# 一、本章概述

本章以ReentrantLock的调用为例，说明AbstractQueuedSynchronizer提供的独占功能。
本章结构如下：

1.  以ReentrantLock的公平策略为例，分析AbstractQueuedSynchronizer的独占功能
2.  以ReentrantLock的非公平策略为例，分析AbstractQueuedSynchronizer的独占功能
3.  分析AbstractQueuedSynchronizer的锁中断、限时等待等功能

## 二、ReentrantLock的公平策略原理

本节对ReentrantLock公平策略的分析基于以下示例：

假设现在有3个线程：ThreadA、ThreadB、ThreadC，一个公平的独占锁，3个线程会依次尝试去获取锁：ReentrantLock lock=new ReentrantLock(true);

线程的操作时序如下：


//ThreadA    lock

//ThreadB    lock

//ThreadC    lock

//ThreadA    release

//ThreadB    release

//ThreadC    release

### 2.1 ThreadA首先获取到锁

最终其实调用了FairSync的lock方法：
 if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
            
        /**
         * Fair version of tryAcquire.  Don't grant access unless
         * recursive call or no waiters or is first.
         */
        protected final boolean tryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                if (!hasQueuedPredecessors() &&
                    compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0)
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }
        
可以看到，在ReentrantLock中，同步状态State的含义如下：

    State	资源的定义
    0	表示锁可用
    1	表示锁被占用
    大于1	表示锁被占用，且值表示同一线程的重入次数
    
ThreadA是首个获取锁的线程，所以上述方法会返回true，第一阶段结束。（ThreadA一直保持占有锁的状态)此时，AQS中的等待队列还是空,并没有生产节点。

### 2.2 ThreadB开始获取锁

tryAcquire方法肯定是返回false（因为此时ThreadA占有着锁）。
接下来看下addWaiter方法，这个方法其实就是将当前调用线程包装成一个【独占结点】，添加到等待队列尾部。
    
    public final void acquire(int arg) {
            if (!tryAcquire(arg) &&
                acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
                selfInterrupt();
        }


    /**
     * Creates and enqueues node for current thread and given mode.
     *
     * @param mode Node.EXCLUSIVE for exclusive, Node.SHARED for shared
     * @return the new node
     */
    private Node addWaiter(Node mode) {
        Node node = new Node(Thread.currentThread(), mode);
        // Try the fast path of enq; backup to full enq on failure 想直接插到尾部，省的再去头那里插
        Node pred = tail;
        if (pred != null) {
            node.prev = pred;
            if (compareAndSetTail(pred, node)) {
                pred.next = node;
                return node;
            }
        }
        enq(node);
        return node;
    }
    
        /**
         * Inserts node into queue, initializing if necessary. See picture above.
         * @param node the node to insert
         * @return node's predecessor
         */
        private Node enq(final Node node) {
            for (;;) {
                Node t = tail;
                if (t == null) { // Must initialize
                // 所以第一次的头节点是没有线程绑定的，但是请求锁的那个线程直接插到了tail位置
                    if (compareAndSetHead(new Node()))
                        tail = head;
                } else {
                    node.prev = t;
                    if (compareAndSetTail(t, node)) {
                        t.next = node;
                        return t;
                    }
                }
            }
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
    
     /**
         * Checks and updates status for a node that failed to acquire.
         * Returns true if thread should block. This is the main signal
         * control in all acquire loops.  Requires that pred == node.prev.
         *
         * @param pred node's predecessor holding status
         * @param node the node
         * @return {@code true} if thread should block
         */
        private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        // B进来的适合ws=0 
            int ws = pred.waitStatus;
            if (ws == Node.SIGNAL)
                /*
                 * This node has already set status asking a release
                 * to signal it, so it can safely park.
                 */
                return true;
            if (ws > 0) {
                /*
                 * Predecessor was cancelled. Skip over predecessors and
                 * indicate retry.
                 */
                do {
                    node.prev = pred = pred.prev;
                } while (pred.waitStatus > 0);
                pred.next = node;
            } else {
                /*
                 * waitStatus must be 0 or PROPAGATE.  Indicate that we
                 * need a signal, but don't park yet.  Caller will need to
                 * retry to make sure it cannot acquire before parking.
                 */
                 // 在这里把之前节点（即队首节点）的值设了-1
                compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
            }
            return false;
        }

在AQS中，等待队列中的线程都是阻塞的，当某个线程被唤醒时，只有该线程是首结点（线程）时，才有权去尝试获取锁。
上述方法中，将ThreadB包装成结点插入队尾后，先判断ThreadB是否是首结点（注意不是头结点，头结点是个dummy结点），
发现确实是首结点（node.predecessor==head），于是调用tryAcquire尝试获取锁，但是获取失败了（此时ThreadA占有着锁），就要判断是否需要阻塞当前线程。
调用 shouldParkAfterFailedAcquire阻塞
    注意，对于独占功能，只使用了3种结点状态：
    
    结点状态	值	描述
    CANCELLED	1	取消。表示后驱结点被中断或超时，需要移出队列
    SIGNAL	-1	发信号。表示后驱结点被阻塞了（当前结点在入队后、阻塞前，应确保将其prev结点类型改为SIGNAL，以便prev结点取消或释放时将当前结点唤醒。）
    CONDITION	-2	Condition专用。表示当前结点在Condition队列中，因为等待某个条件而被阻塞了

对于在等待队列中的线程，如果要阻塞它，需要确保将来有线程可以唤醒它，AQS中通过将前驱结点的状态置为SIGNAL:-1来表示将来会唤醒当前线程，当前线程可以安心的阻塞。
至此，ThreadB的执行也暂告一段落了（安心得在等待队列中睡觉）。

注意：补充一点，如果ThreadB在阻塞过程中被中断，其实是不会抛出异常的，只会在acquireQueued方法返回时，
告诉调用者在阻塞器件有没被中断过，具体如果处理，要不要抛出异常，取决于调用者，这其实是一种延时中断机制。

### 2.3 ThreadC开始获取锁

终于轮到ThreadC出场了，ThreadC的调用过程和ThreadB完全一样，同样拿不到锁，然后加入到等待队列队尾：
然后，ThreadC在阻塞前需要把前驱结点的状态置为SIGNAL：-1，以确保将来可以被唤醒：（也就是把B的值设为了-1）
至此，ThreadC的执行也暂告一段落了（安心得在等待队列中睡觉）。

### 2.4 ThreadA释放锁

ThreadA终于使用完了临界资源，要释放锁了，来看下ReentrantLock的unlock方法：
        
          * Releases in exclusive mode.  Implemented by unblocking one or
             * more threads if {@link #tryRelease} returns true.
             * This method can be used to implement method {@link Lock#unlock}.
             *
             * @param arg the release argument.  This value is conveyed to
             *        {@link #tryRelease} but is otherwise uninterpreted and
             *        can represent anything you like.
             * @return the value returned from {@link #tryRelease}
             */
            public final boolean release(int arg) {
                if (tryRelease(arg)) {
                    Node h = head;
                    if (h != null && h.waitStatus != 0)
                        unparkSuccessor(h);
                    return true;
                }
                return false;
            }
            
            
           protected final boolean tryRelease(int releases) {
                     int c = getState() - releases;
                     if (Thread.currentThread() != getExclusiveOwnerThread())
                         throw new IllegalMonitorStateException();
                     boolean free = false;
                     if (c == 0) {
                         free = true;
                         setExclusiveOwnerThread(null);
                     }
                     setState(c);
                     return free;
                 }
   
释放成功后，调用unparkSuccessor方法，唤醒队列中的首结点：
        
        
            /**
             * Wakes up node's successor, if one exists.
             *
             * @param node the node
             */
            private void unparkSuccessor(Node node) {
                /*
                 * If status is negative (i.e., possibly needing signal) try
                 * to clear in anticipation of signalling.  It is OK if this
                 * fails or if status is changed by waiting thread.
                 */
                int ws = node.waitStatus;
                if (ws < 0)
                    //之前节点值设为0
                    compareAndSetWaitStatus(node, ws, 0);
        
                /*
                 * Thread to unpark is held in successor, which is normally
                 * just the next node.  But if cancelled or apparently null,
                 * traverse backwards from tail to find the actual
                 * non-cancelled successor.
                 */
                Node s = node.next;
                if (s == null || s.waitStatus > 0) {
                    s = null;
                    for (Node t = tail; t != null && t != node; t = t.prev)
                        if (t.waitStatus <= 0)
                            s = t;
                }
                if (s != null)
                    LockSupport.unpark(s.thread);
            }

## 2.5 ThreadB唤醒后继续执行

好了，队首结点（ThreadB）被唤醒了。
ThreadB会继续从以下位置开始执行，先返回一个中断标识，用于表示ThreadB在阻塞期间有没被中断过：

        /**
         * Convenience method to park and then check if interrupted
         *
         * @return {@code true} if interrupted
         */
        private final boolean parkAndCheckInterrupt() {
            LockSupport.park(this);
            return Thread.interrupted();
        }

然后ThreadB又开始了自旋操作，被唤醒的是队首结点，所以可以尝试tryAcquire获取锁，此时获取成功（ThreadA已经释放了锁）。
获取成功后会调用setHead方法，将头结点置为当前结点，并清除线程信息：B为-1，C为0

### 2.6 ThreadB释放锁

ThreadB也终于使用完了临界资源，要释放锁了，过程和ThreadA释放时一样，释放成功后，会调用unparkSuccessor方法，唤醒队列中的首结点：
队首结点（ThreadC）被唤醒后，继续从原来的阻塞处向下执行，并尝试获取锁，获取成功，最终队列状态如下：

## 2.7 ThreadC释放锁

ThreadC也终于使用完了临界资源，要释放锁了。释放成功后，调用unparkSuccessor方法，唤醒队列中的首结点：
此时队列中只剩下一个头结点（dummy），所以这个方法其实什么都不做。最终队列的状态就是只有一个dummy头结点

至此，AQS的独占功能已经差不多分析完了，剩下还有几个内容没分析：

+   锁中断功能
+   限时等待功能
+   Conditon等待功能
这些功能将在后续章节陆续分析。

## 三、ReentrantLock的非公平策略原理

ReenrantLock非公平策略的内部实现和公平策略没啥太大区别：
非公平策略和公平策略的最主要区别在于：

+   公平锁获取锁时，会判断等待队列中是否有线程排在当前线程前面。只有没有情况下，才去获取锁，这是公平的含义。通过这个来判定之前是否存在节点hasQueuedPredecessors()；
+   非公平锁获取锁时，会立即尝试修改同步状态，失败后再调用AQS的acquire方法。

acquire方法会转调非公平锁自身的tryAcquire方法，其实最终是调了nofairTryAcquire方法，而该方法相对于公平锁，只是少了“队列中是否有其它线程排在当前线程前”这一判断：

### 四、AQS对中断的支持

还是以ReentrantLock为例，来看下AQS是如何实现锁中断和超时的。
我们知道ReentrantLock的lockInterruptibly方法是会响应中断的。（线程如果在阻塞过程中被中断，会抛出InterruptedException异常）

该方法调用了AQS的acquireInterruptibly方法：
        
        /**
             * Acquires in exclusive mode, aborting if interrupted.
             * Implemented by first checking interrupt status, then invoking
             * at least once {@link #tryAcquire}, returning on
             * success.  Otherwise the thread is queued, possibly repeatedly
             * blocking and unblocking, invoking {@link #tryAcquire}
             * until success or the thread is interrupted.  This method can be
             * used to implement method {@link Lock#lockInterruptibly}.
             *
             * @param arg the acquire argument.  This value is conveyed to
             *        {@link #tryAcquire} but is otherwise uninterpreted and
             *        can represent anything you like.
             * @throws InterruptedException if the current thread is interrupted
             */
            public final void acquireInterruptibly(int arg)
                    throws InterruptedException {
                if (Thread.interrupted())
                    throw new InterruptedException();
                if (!tryAcquire(arg))
                    doAcquireInterruptibly(arg);
            }
            
            /**
             * Acquires in exclusive interruptible mode.
             * @param arg the acquire argument
             */
            private void doAcquireInterruptibly(int arg)
                throws InterruptedException {
                final Node node = addWaiter(Node.EXCLUSIVE);
                boolean failed = true;
                try {
                    for (;;) {
                        final Node p = node.predecessor();
                        if (p == head && tryAcquire(arg)) {
                            setHead(node);
                            p.next = null; // help GC
                            failed = false;
                            return;
                        }
                        if (shouldParkAfterFailedAcquire(p, node) &&
                            parkAndCheckInterrupt())
                            throw new InterruptedException();
                    }
                } finally {
                    if (failed)
                        cancelAcquire(node);
                }
            }
            
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
            
很眼熟有木有？看下和acquireQueued方法的对比，唯一的区别就是：
当调用线程获取锁失败，进入阻塞后，如果中途被中断，acquireQueued只是用一个标识记录线程被中断过，而doAcquireInterruptibly则是直接抛出异常

## 五、AQS对限时等待的支持

Lock接口中有一个方法：tryLock，用于在指定的时间内尝试获取锁，获取不到就返回。
ReentrantLock实现了该方法，可以看到，该方法内部调用了AQS的tryAcquireNanos方法：
    
     public boolean tryLock(long timeout, TimeUnit unit)
                throws InterruptedException {
            return sync.tryAcquireNanos(1, unit.toNanos(timeout));
        }
    
     
     public final boolean tryAcquireNanos(int arg, long nanosTimeout)
                 throws InterruptedException {
             if (Thread.interrupted())
                 throw new InterruptedException();
             return tryAcquire(arg) ||
                 doAcquireNanos(arg, nanosTimeout);
         }

     
       /**
          * Acquires in exclusive timed mode.
          *
          * @param arg the acquire argument
          * @param nanosTimeout max wait time
          * @return {@code true} if acquired
          */
         private boolean doAcquireNanos(int arg, long nanosTimeout)
                 throws InterruptedException {
             if (nanosTimeout <= 0L)
                 return false;
             final long deadline = System.nanoTime() + nanosTimeout;
             final Node node = addWaiter(Node.EXCLUSIVE);
             boolean failed = true;
             try {
                 for (;;) {
                     final Node p = node.predecessor();
                     if (p == head && tryAcquire(arg)) {
                         setHead(node);
                         p.next = null; // help GC
                         failed = false;
                         return true;
                     }
                     nanosTimeout = deadline - System.nanoTime();
                     if (nanosTimeout <= 0L)
                         return false;
                     if (shouldParkAfterFailedAcquire(p, node) &&
                         nanosTimeout > spinForTimeoutThreshold)
                         LockSupport.parkNanos(this, nanosTimeout);
                     if (Thread.interrupted())
                         throw new InterruptedException();
                 }
             } finally {
                 if (failed)
                     cancelAcquire(node);
             }
         }
     
        /**
          * Cancels an ongoing attempt to acquire.
          *
          * @param node the node
          */
         private void cancelAcquire(Node node) {
             // Ignore if node doesn't exist
             if (node == null)
                 return;
     
             node.thread = null;
     
             // Skip cancelled predecessors
             Node pred = node.prev;
             while (pred.waitStatus > 0) 因为1是取消
                 node.prev = pred = pred.prev;
     
             // predNext is the apparent node to unsplice. CASes below will
             // fail if not, in which case, we lost race vs another cancel
             // or signal, so no further action is necessary.
             Node predNext = pred.next;
     
             // Can use unconditional write instead of CAS here.
             // After this atomic step, other Nodes can skip past us.
             // Before, we are free of interference from other threads.
             node.waitStatus = Node.CANCELLED;
     
             // If we are the tail, remove ourselves.
             if (node == tail && compareAndSetTail(node, pred)) {
                 compareAndSetNext(pred, predNext, null);
             } else {
                 // If successor needs signal, try to set pred's next-link
                 // so it will get one. Otherwise wake it up to propagate.
                 int ws;
                 if (pred != head &&
                     ((ws = pred.waitStatus) == Node.SIGNAL ||
                      (ws <= 0 && compareAndSetWaitStatus(pred, ws, Node.SIGNAL))) &&
                     pred.thread != null) {
                     Node next = node.next;
                     if (next != null && next.waitStatus <= 0)
                         compareAndSetNext(pred, predNext, next);
                 } else {
                     unparkSuccessor(node);
                 }
     
                 node.next = node; // help GC
             }
         }

    






