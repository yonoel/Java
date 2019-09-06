# 一、本章概述

AQS系列的前四个章节，已经分析了AQS的原理，本章将会从ReentrantReadWriteLock出发，给出其内部利用AQS框架的实现原理。

ReentrantReadWriteLock（以下简称RRW），也就是读写锁，是一个比较特殊的同步器，
特殊之处在于其对同步状态State的定义与ReentrantLock、CountDownLatch都很不同。
通过RRW的分析，我们可以更深刻的了解AQS框架的设计思想，以及对“什么是资源？如何定义资源是否可以被访问？”这一命题有更深刻的理解。

## 二、本章示例

和之前的章节一样，本章也通过示例来分析RRW的源码。

假设现在有4个线程，ThreadA、ThreadB、ThreadC、ThreadD。
ThreadA、ThreadB、ThreadD为读线程，ThreadC为写线程：
初始时，构造RRM对象：
private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
private final Lock r = rwl.readLock();
private final Lock w = rwl.writeLock();

//ThreadA调用读锁的lock()方法

//ThreadB调用读锁的lock()方法

//ThreadC调用写锁的lock()方法

//ThreadD调用读锁的lock()方法

## 三、RRW的公平策略原理

### 1. RRW对象的创建

和ReentrantLock类似，ReentrantReadWriteLock的构造器可以选择公平/非公平策略（默认为非公平策略），
RRW内部的FairSync、NonfairSync是AQS的两个子类，分别代表了实现公平策略和非公平策略的同步器：
    
     static final class FairSync extends Sync {
            private static final long serialVersionUID = -2274990926593161451L;
            final boolean writerShouldBlock() {
                return hasQueuedPredecessors();
            }
            final boolean readerShouldBlock() {
                return hasQueuedPredecessors();
            }
        }
    
     static final class NonfairSync extends Sync {
            private static final long serialVersionUID = -8159625535654395037L;
            final boolean writerShouldBlock() {
                return false; // writers can always barge
            }
            final boolean readerShouldBlock() {
                /* As a heuristic to avoid indefinite writer starvation,
                 * block if the thread that momentarily appears to be head
                 * of queue, if one exists, is a waiting writer.  This is
                 * only a probabilistic effect since a new reader will not
                 * block if there is a waiting writer behind other enabled
                 * readers that have not yet drained from the queue.
                 */
                return apparentlyFirstQueuedIsExclusive();
            }
        }
ReentrantReadWriteLock.ReadLock和ReentrantReadWriteLock.WriteLock其实就是两个实现了Lock接口的内部类:

### 2. ThreadA调用读锁的lock()方法
    
读锁其实是一种共享锁，实现了AQS的共享功能API，可以看到读锁的内部就是调用了AQS的acquireShared方法，该方法前面几章我们已经见过太多次了：
关键来看下ReentrantReadWriteLock是如何实现tryAcquireShared方法的：
    
读锁获取成功的条件如下：

+   写锁没有被其它线程占用（可被当前线程占用，这种情况属于锁降级）
+   等待队列中的队首没有其它线程（公平策略）
+   读锁重入次数没有达到最大值
+   CAS操作修改同步状态值State成功


            public void lock() {
                sync.acquireShared(1);
            }
        
        public final void acquireShared(int arg) {
                if (tryAcquireShared(arg) < 0)
                    doAcquireShared(arg);
            }
            
         protected final int tryAcquireShared(int unused) {
                   
                    Thread current = Thread.currentThread();
                    int c = getState();  // 如果第一次进来返回65536。。。最大值
                    if (exclusiveCount(c) != 0 &&
                        getExclusiveOwnerThread() != current)
                        return -1; 不是当前线程，直接返回失败
                    int r = sharedCount(c); 读锁的重入次数（有几个读锁）
                    if (!readerShouldBlock() && 是否阻塞
                        r < MAX_COUNT && 
                        compareAndSetState(c, c + SHARED_UNIT)) { cas操作，操作数+1
                        if (r == 0) { 第一次为0
                            firstReader = current;
                            firstReaderHoldCount = 1;
                        } else if (firstReader == current) {
                            firstReaderHoldCount++;
                        } else {
                            HoldCounter rh = cachedHoldCounter;
                            if (rh == null || rh.tid != getThreadId(current))
                                cachedHoldCounter = rh = readHolds.get();
                            else if (rh.count == 0)
                                readHolds.set(rh);
                            rh.count++;
                        }
                        return 1;
                    }
                    return fullTryAcquireShared(current);
                }
                
        static final class FairSync extends Sync {
                private static final long serialVersionUID = -2274990926593161451L;
                final boolean writerShouldBlock() {
                    return hasQueuedPredecessors();
                }
                final boolean readerShouldBlock() {
                    return hasQueuedPredecessors();
                }
            }
        
        public final boolean hasQueuedPredecessors() {
                // The correctness of this depends on head being initialized
                // before tail and on head.next being accurate if the current
                // thread is first in queue.
                Node t = tail; // Read fields in reverse initialization order
                Node h = head;
                Node s;
                return h != t &&
                    ((s = h.next) == null || s.thread != Thread.currentThread());
            }
            
如果CAS操作失败（并发导致），会调用fullTryAcquireShared方法，自旋修改State值：
        
        /**
                 * Full version of acquire for reads, that handles CAS misses
                 * and reentrant reads not dealt with in tryAcquireShared.
                 */
                final int fullTryAcquireShared(Thread current) {
                    /*
                     * This code is in part redundant with that in
                     * tryAcquireShared but is simpler overall by not
                     * complicating tryAcquireShared with interactions between
                     * retries and lazily reading hold counts.
                     */
                    HoldCounter rh = null;
                    for (;;) {
                        int c = getState();
                        if (exclusiveCount(c) != 0) { 有线程占用，应该是写锁
                            if (getExclusiveOwnerThread() != current) 如果占用的不是写锁
                                return -1;
                            // else we hold the exclusive lock; blocking here
                            // would cause deadlock.
                        } else if (readerShouldBlock()) { 判定当前是否需要等待
                            // Make sure we're not acquiring read lock reentrantly
                            if (firstReader == current) {
                                // assert firstReaderHoldCount > 0;
                            } else {
                                // 读锁时，rh为空
                                if (rh == null) {
                                    rh = cachedHoldCounter;
                                    if (rh == null || rh.tid != getThreadId(current)) {
                                        rh = readHolds.get();
                                        if (rh.count == 0)
                                            readHolds.remove();
                                    }
                                }
                                if (rh.count == 0)
                                    return -1;
                            }
                        }
                        if (sharedCount(c) == MAX_COUNT)
                            throw new Error("Maximum lock count exceeded");
                        if (compareAndSetState(c, c + SHARED_UNIT)) {
                            if (sharedCount(c) == 0) {
                                firstReader = current;
                                firstReaderHoldCount = 1;
                            } else if (firstReader == current) {
                                firstReaderHoldCount++;
                            } else {
                                if (rh == null)
                                    rh = cachedHoldCounter;
                                if (rh == null || rh.tid != getThreadId(current))
                                    rh = readHolds.get();
                                else if (rh.count == 0)
                                    readHolds.set(rh);
                                rh.count++;
                                cachedHoldCounter = rh; // cache for release
                            }
                            return 1;
                        }
                    }
                }

因此ThreadA进来后，条件队列头尾指向null，但是有了firstReader，state=65536，firstReaderHoldCount = 1;

### 3. ThreadB调用读锁的lock()方法

由于读锁是共享锁，且此时写锁未被占用，所以此时ThreadB也可以拿到读锁。state=135.。。，count = 2,条件队列依旧指向null

### 4. ThreadC调用写锁的lock()方法

写锁其实是一种独占锁，实现了AQS的独占功能API，可以看到写锁的内部就是调用了AQS的acquire方法，这就是创建队列节点了。。
    
     public void lock() {
                 sync.acquire(1);
             }


    public final void acquire(int arg) {
            if (!tryAcquire(arg) &&
                acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
                selfInterrupt();
        }
关键来看下ReentrantReadWriteLock是如何实现tryAcquire方法的，并没有什么特别，就是区分了两种情况：

1.  当前线程已经持有写锁
2.  写锁未被占用


    protected final boolean tryAcquire(int acquires) {
           
            Thread current = Thread.currentThread();
            int c = getState();
            int w = exclusiveCount(c);
            if (c != 0) {
                // (Note: if c != 0 and w == 0 then shared count != 0)
                if (w == 0 || current != getExclusiveOwnerThread()) 占用者不是当前线程
                    return false;
                if (w + exclusiveCount(acquires) > MAX_COUNT) 超过使用次数
                    throw new Error("Maximum lock count exceeded");
                // Reentrant acquire
                setState(c + acquires); 
                return true;
            }
            if (writerShouldBlock() || 判断当前是否需要阻塞（公平）
                !compareAndSetState(c, c + acquires)) cas更新值
                return false;
            setExclusiveOwnerThread(current);
            return true;
        }

      /**
         * Acquires in exclusive uninterruptible mode for thread already in
         * queue. Used by condition wait methods as well as acquire.
         *
         * @param node the node
         * @param arg the acquire argument
         * @return {@code true} if interrupted while waiting
         */
        final boolean acquireQueued(final Node node, int arg) {
            boolean failed = true;
            try {
                boolean interrupted = false;
                for (;;) {
                    final Node p = node.predecessor();
                    if (p == head && tryAcquire(arg)) {
                        setHead(node);
                        p.next = null; // help GC
                        failed = false;
                        return interrupted;
                    }
                    if (shouldParkAfterFailedAcquire(p, node) &&
                        parkAndCheckInterrupt())
                        interrupted = true;
                }
            } finally {
                if (failed)
                    cancelAcquire(node);
            }
        }
ThreadC调用完lock方法后，由于存在使用中的读锁，所以会调用acquireQueued并被加入等待队列，这个过程就是独占锁的请求过程（AQS[二]），等待队列结构如下：
头为dummy，tail为写的节点

此时：
写锁数量：0
读锁数量：2

### 5. ThreadD调用读锁的lock()方法

这个过程和ThreadA和ThreadB几乎一样，读锁是共享锁，可以重复获取，但是有一点区别：
由于等待队列中已经有其它线程（ThreadC）排在当前线程前，所以readerShouldblock方法会返回true，这是公平策略的含义。
！！！虽然获取失败了，但是后续调用fullTryAcquireShared方法，自旋修改State值，正常情况下最终修改成功，代表获取到读锁：

条件队列还是没有改变。

### 6. ThreadA释放读锁

内部就是调用了AQS的releaseShared方法，该方法前面几章我们已经见过太多次了：
    
    public void unlock() {
                sync.releaseShared(1);
            }
            
      */
        public final boolean releaseShared(int arg) {
            if (tryReleaseShared(arg)) {
                doReleaseShared();
                return true;
            }
            return false;
        }

     protected final boolean tryReleaseShared(int unused) {
                Thread current = Thread.currentThread();
                if (firstReader == current) {
                    // assert firstReaderHoldCount > 0;
                    if (firstReaderHoldCount == 1)
                        firstReader = null;
                    else
                        firstReaderHoldCount--;
                } else {
                    HoldCounter rh = cachedHoldCounter;
                    if (rh == null || rh.tid != getThreadId(current))
                        rh = readHolds.get();
                    int count = rh.count;
                    if (count <= 1) {
                        readHolds.remove();
                        if (count <= 0)
                            throw unmatchedUnlockException();
                    }
                    --rh.count;
                }
                for (;;) {
                    int c = getState();
                    int nextc = c - SHARED_UNIT;
                    if (compareAndSetState(c, nextc))
                        // Releasing the read lock has no effect on readers,
                        // but it may allow waiting writers to proceed if
                        // both read and write locks are now free.
                        return nextc == 0;
                }
            }

HoldCounter是个内部类，通过与ThreadLocal结合使用保存每个线程的持有读锁数量，其实是一种优化手段。

### 7. ThreadB释放读锁

和ThreadA的释放完全一样

### 8. ThreadD释放读锁

和ThreadA的释放几乎一样，不同的是此时读锁数量为0，tryReleaseShared方法返回true调用doReleaseShared
因此，会继续调用doReleaseShared方法，doReleaseShared方法之前在讲AQS[四]时已经阐述过了，就是一个自旋操作：
该操作会将ThreadC唤醒：


     private void doReleaseShared() {
            
            for (;;) {
                Node h = head;
                if (h != null && h != tail) {
                    int ws = h.waitStatus;
                    if (ws == Node.SIGNAL) {
                        if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                            continue;            // loop to recheck cases
                        unparkSuccessor(h);
                    }
                    else if (ws == 0 &&
                             !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                        continue;                // loop on failed CAS
                }
                if (h == head)                   // loop if head changed
                    break;
            }
        }

### 9. ThreadC从原阻塞处继续向下执行

ThreadC从原阻塞处被唤醒后，进入下一次自旋操作，然后调用tryAcquire方法获取写锁成功，并从队列中移除:

### 10. ThreadC释放写锁

其实就是独占锁的释放，在AQS[二]中，已经阐述过了，不再赘述。

补充一点：如果头结点后面还有等待的共享结点，会以传播的方式依次唤醒，这个过程就是共享结点的唤醒过程，并无区别。

## 四、总结

本章通过ReentrantReadWriteLock的公平策略，分析了RRW的源码，非公平策略分析方法也是一样的，非公平和公平的最大区别在于写锁的获取上：

在非公平策略中，写锁的获取永远不需要排队，这其实时性能优化的考虑，因为大多数情况写锁涉及的操作时间耗时要远大于读锁，频次远低于读锁，这样可以防止写线程一直处于饥饿状态。

关于ReentrantReadWriteLock，最后有两点规律需要注意：

+   当RRW的等待队列队首结点是共享结点，说明当前写锁被占用，当写锁释放时，会以传播的方式唤醒头结点之后紧邻的各个共享结点。
+   当RRW的等待队列队首结点是独占结点，说明当前读锁被使用，当读锁释放归零后，会唤醒队首的独占结点。
ReentrantReadWriteLock的特殊之处其实就是用一个int值表示两种不同的状态（低16位表示写锁的重入次数，高16位表示读锁的使用次数），
并通过两个内部类同时实现了AQS的两套API，核心部分与共享/独占锁并无什么区别。







