# 一、本章概述

AQS系列的前三个章节，我们通过ReentrantLock的示例，分析了AQS的独占功能。
本章将以CountDownLatch为例，分析AQS的共享功能。

CountDownLatch示例
假设现在有3个线程，ThreadA、ThreadB、mainThread，CountDownLatch初始计数为1：
CountDownLatch switcher = new CountDownLatch(1);
线程的调用时序如下：

//ThreadA调用await()方法等待

//ThreadB调用await()方法等待

//主线程main调用countDown()放行

## 二、AQS共享功能的原理

### 1. 创建CountDownLatch

CountDownLatch的创建没什么特殊，调用唯一的构造器，传入一个初始计数值，内部实例化一个AQS子类：
    
    public CountDownLatch(int count) {
        if (count < 0) throw new IllegalArgumentException("count < 0");
        this.sync = new Sync(count);
    }

     Sync(int count) {
                setState(count);
            }

可以看到，初始计数值count其实就是同步状态值，在CountDownLatch中，同步状态State表示CountDownLatch的计数器的初始大小。

### 2. ThreadA调用await()方法等待
CountDownLatch的await方法是响应中断的，该方法其实是调用了AQS的acquireSharedInterruptibly方法：

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

     public final void acquireSharedInterruptibly(int arg)
                throws InterruptedException {
            if (Thread.interrupted())
                throw new InterruptedException();
            if (tryAcquireShared(arg) < 0)
                doAcquireSharedInterruptibly(arg);
        }
注意tryAcquireShared方法，该方法尝试获取锁，由AQS子类实现，其返回值的含义如下：

    State	资源的定义
    小于0	表示获取失败
    0	表示获取成功
    大于0	表示获取成功，且后继争用线程可能成功

    
     protected int tryAcquireShared(int acquires) {
                return (getState() == 0) ? 1 : -1;
            }
    
我们之前说了在CountDownLatch中，同步状态State表示CountDownLatch的计数器的初始值，
当State==0时，表示无锁状态，且一旦State变为0，就永远处于无锁状态了，此时所有线程在await上等待的线程都可以继续执行。
而在ReentrantLock中，State==0时，虽然也表示无锁状态，但是只有一个线程可以重置State的值。这就是共享锁的含义。

好了，继续向下执行，ThreadA尝试获取锁失败后，会调用doAcquireSharedInterruptibly：
    
        /**
         * Acquires in shared interruptible mode.
         * @param arg the acquire argument
         */
        private void doAcquireSharedInterruptibly(int arg)
            throws InterruptedException {
            final Node node = addWaiter(Node.SHARED); 插入队列 此时头节点和尾节点值都为0
            boolean failed = true;
            try {
                for (;;) {
                    final Node p = node.predecessor();
                    if (p == head) {
                        int r = tryAcquireShared(arg); 获取锁，大于0才是成功
                        if (r >= 0) {
                            setHeadAndPropagate(node, r);
                            p.next = null; // help GC
                            failed = false;
                            return;
                        }
                    }
                    if (shouldParkAfterFailedAcquire(p, node) && 是否需要阻塞？
                        parkAndCheckInterrupt()) 
                        throw new InterruptedException();
                }
            } finally {
                if (failed)
                    cancelAcquire(node);
            }
        }
        
然后会进入自旋操作，先尝试获取一次锁，显然此时是获取失败的（主线程main还未调用countDown，同步状态State还是1）。
然后判断是否要进入阻塞（shouldParkAfterFailedAcquire）：
    
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
                compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
            }
            return false;
        }
好了，至此，ThreadA进入阻塞态，最终队列结构如下,头节点为压节点，值-1，tail为线程a节点，值为0，state为1

### 3. ThreadB调用await()方法等待

流程和步骤2完全相同，调用后ThreadB也被加入到等待队列中：A的值为-1，B为0

### 4. 主线程main调用countDown()放行

ThreadA和ThreadB调用了await()方法后都在等待了，现在主线程main开始调用countDown()方法，
该方法调用后，ThreadA和ThreadB都会被唤醒，并继续往下执行，达到类似门栓的作用。

        public void countDown() {
            sync.releaseShared(1);
        }

        
          /**
             * Releases in shared mode.  Implemented by unblocking one or more
             * threads if {@link #tryReleaseShared} returns true.
             *
             * @param arg the release argument.  This value is conveyed to
             *        {@link #tryReleaseShared} but is otherwise uninterpreted
             *        and can represent anything you like.
             * @return the value returned from {@link #tryReleaseShared}
             */
            public final boolean releaseShared(int arg) {
                if (tryReleaseShared(arg)) {
                    doReleaseShared();
                    return true;
                }
                return false;
            }
        
         protected boolean tryReleaseShared(int releases) {
                    // Decrement count; signal when transition to zero
                    for (;;) {
                        int c = getState();
                        if (c == 0)
                            return false;
                        int nextc = c-1;
                        if (compareAndSetState(c, nextc))
                            return nextc == 0;
                    }
                }
 
         /**
             * Release action for shared mode -- signals successor and ensures
             * propagation. (Note: For exclusive mode, release just amounts
             * to calling unparkSuccessor of head if it needs signal.)
             */
            private void doReleaseShared() {
                /*
                 * Ensure that a release propagates, even if there are other
                 * in-progress acquires/releases.  This proceeds in the usual
                 * way of trying to unparkSuccessor of head if it needs
                 * signal. But if it does not, status is set to PROPAGATE to
                 * ensure that upon release, propagation continues.
                 * Additionally, we must loop in case a new node is added
                 * while we are doing this. Also, unlike other uses of
                 * unparkSuccessor, we need to know if CAS to reset status
                 * fails, if so rechecking.
                 */
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

先调用compareAndSetWaitStatus将头结点的等待状态置为0，表示将唤醒后续结点（ThreadA），成功后头为0，A为-1，B为0

然后调用unparkSuccessor唤醒后继结点（ThreadA被唤醒后会从原阻塞处继续往下执行，这个在步骤5再讲）：

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
            
### 5. ThreadA从原阻塞处继续向下执行

ThreadA被唤醒后，会从原来的阻塞处继续向下执行：
由于是一个自旋操作，ThreadA会再次尝试获取锁，由于此时State同步状态值为0（无锁状态），所以获取成功。然后调用setHeadAndPropagate方法：

     /**
         * Acquires in shared interruptible mode.
         * @param arg the acquire argument
         */
        private void doAcquireSharedInterruptibly(int arg)
            throws InterruptedException {
            final Node node = addWaiter(Node.SHARED);
            boolean failed = true;
            try {
                for (;;) {
                    final Node p = node.predecessor();
                    if (p == head) { 这一次成为了头节点
                        int r = tryAcquireShared(arg); 释放了
                        if (r >= 0) { r = 1
                            setHeadAndPropagate(node, r);
                            p.next = null; // help GC
                            failed = false;
                            return;
                        }
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
        
        /**
             * Sets head of queue, and checks if successor may be waiting
             * in shared mode, if so propagating if either propagate > 0 or
             * PROPAGATE status was set.
             *
             * @param node the node
             * @param propagate the return value from a tryAcquireShared
             */
            private void setHeadAndPropagate(Node node, int propagate) {
                Node h = head; // Record old head for check below
                setHead(node);
                /*
                 * Try to signal next queued node if:
                 *   Propagation was indicated by caller,
                 *     or was recorded (as h.waitStatus either before
                 *     or after setHead) by a previous operation
                 *     (note: this uses sign-check of waitStatus because
                 *      PROPAGATE status may transition to SIGNAL.)
                 * and
                 *   The next node is waiting in shared mode,
                 *     or we don't know, because it appears null
                 *
                 * The conservatism in both of these checks may cause
                 * unnecessary wake-ups, but only when there are multiple
                 * racing acquires/releases, so most need signals now or soon
                 * anyway.
                 */
                if (propagate > 0 || h == null || h.waitStatus < 0 ||
                    (h = head) == null || h.waitStatus < 0) {
                    Node s = node.next;
                    if (s == null || s.isShared())
                        doReleaseShared();
                }
            }

     /**
         * Sets head of queue to be node, thus dequeuing. Called only by
         * acquire methods.  Also nulls out unused fields for sake of GC
         * and to suppress unnecessary signals and traversals.
         *
         * @param node the node
         */
        private void setHead(Node node) {
            head = node;
            node.thread = null;
            node.prev = null;
        }
将A变成了头节点，调用doReleaseShared方法，释放并唤醒ThreadB结点

### 6. ThreadB从原阻塞处继续向下执行

ThreadB被唤醒后，从原阻塞处继续向下执行，这个过程和步骤5（ThreadA唤醒后继续执行）完全一样。

setHeadAndPropagate方法把ThreadB结点变为头结点，并根据传播状态判断是否要唤醒并释放后继结点，B变成了头节点。
调用doReleaseShared方法，释放并唤醒后继结点（此时没有后继结点了，则直接break）。

## 三、总结

AQS的共享功能，通过钩子方法tryAcquireShared暴露，与独占功能最主要的区别就是：

共享功能的结点，一旦被唤醒，会向队列后部传播（Propagate）状态，以实现共享结点的连续唤醒。
这也是共享的含义，当锁被释放时，所有持有该锁的共享线程都会被唤醒，并从等待队列移除。





    

