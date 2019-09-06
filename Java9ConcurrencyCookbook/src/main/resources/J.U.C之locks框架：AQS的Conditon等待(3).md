# 一、本章概述

本章将继续以ReentrantLock的调用为例，说明AbstractQueuedSynchronizer提供的Conditon等待功能。

## 二、Condition接口的实现

J.U.C包提供了Conditon接口，用以对原生的Object.wait()、Object.notify()进行增强。

Condition接口的实现类其实是在AQS中——ConditionObject，ReentranLock的newConditon方法其实是创建了一个AbstractQueuedSynchronizer.ConditionObject对象

Condition作为AQS的内部类，复用了AQS的结点，维护一个条件队列，队列初始时的结构如下：
    
        public class ConditionObject implements Condition, java.io.Serializable {
               private static final long serialVersionUID = 1173984872572414699L;
               /** First node of condition queue. */
               private transient Node firstWaiter;
               /** Last node of condition queue. */
               private transient Node lastWaiter;
       
               /**
                * Creates a new {@code ConditionObject} instance.
                */
               public ConditionObject() { }
        }
        
假设现在有3个线程：ThreadA、ThreadB、ThreadC，一个Conditon实现对象。
ReentrantLock lock = new ReentrantLock();
Conditon con = lock.newConditon();

线程将以以下的时序调用：

//ThreadA先调用lock方法获取到锁，然后调用con.await()

//ThreadB获取锁，调用con.signal()唤醒ThreadA

//ThreadB释放锁

### 1. ThreadA获取到锁后，首先调用await方法

            /**
                 * Implements interruptible condition wait.
                 * <ol>
                 * <li> If current thread is interrupted, throw InterruptedException.
                 * <li> Save lock state returned by {@link #getState}.
                 * <li> Invoke {@link #release} with saved state as argument,
                 *      throwing IllegalMonitorStateException if it fails.
                 * <li> Block until signalled or interrupted.
                 * <li> Reacquire by invoking specialized version of
                 *      {@link #acquire} with saved state as argument.
                 * <li> If interrupted while blocked in step 4, throw InterruptedException.
                 * </ol>
                 */
                public final void await() throws InterruptedException {
                    if (Thread.interrupted()) 响应中断
                        throw new InterruptedException();
                    Node node = addConditionWaiter(); 添加到条件队列里
                    int savedState = fullyRelease(node); 释放锁，返回释放前的状态 第一次为1
                    int interruptMode = 0; 
                    while (!isOnSyncQueue(node)) { 如果节点不在队列里，阻塞
                        LockSupport.park(this);
                        if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                            break;
                    }
                    if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
                        interruptMode = REINTERRUPT;
                    if (node.nextWaiter != null) // clean up if cancelled
                        unlinkCancelledWaiters();
                    if (interruptMode != 0)
                        reportInterruptAfterWait(interruptMode);
                }
            
                    /**
                     * Adds a new waiter to wait queue.
                     * @return its new wait node
                     */
                    private Node addConditionWaiter() {
                        Node t = lastWaiter;
                        // If lastWaiter is cancelled, clean out.
                        if (t != null && t.waitStatus != Node.CONDITION) {
                            unlinkCancelledWaiters();
                            t = lastWaiter;
                        }
                        Node node = new Node(Thread.currentThread(), Node.CONDITION);
                        /第一次进来就为空，增加节点，而且不是亚节点，直接指向了当前线程
                        if (t == null)
                            firstWaiter = node;
                        else
                            t.nextWaiter = node;
                        lastWaiter = node;
                        return node;
                    }
                    
             final int fullyRelease(Node node) {
                    boolean failed = true;
                    try {
                        int savedState = getState(); //第一次1(lock里的队列设置的)
                        if (release(savedState)) {
                            failed = false;
                            return savedState;
                        } else {
                            throw new IllegalMonitorStateException();
                        }
                    } finally {
                        // 第一次为false
                        if (failed)
                            node.waitStatus = Node.CANCELLED;
                    }
                }

             /**
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
                        // 第一次上锁没有head
                        if (h != null && h.waitStatus != 0)
                            unparkSuccessor(h);
                        return true;
                    }
                    return false;
                }
            
             protected final boolean tryRelease(int releases) {
                        int c = getState() - releases;
                            // 使用condition对象，要先上lock，设置锁节点
                        if (Thread.currentThread() != getExclusiveOwnerThread())
                            throw new IllegalMonitorStateException();
                        boolean free = false;
                        if (c == 0) {
                        // 第一次 1-1 =0
                            free = true;
                            setExclusiveOwnerThread(null);
                        }
                        setState(c);
                        return free;
                    }

                /**
                 * Returns true if a node, always one that was initially placed on
                 * a condition queue, is now waiting to reacquire on sync queue.
                 * @param node the node
                 * @return true if is reacquiring
                 */
                final boolean isOnSyncQueue(Node node) {
                // 第一次锁阻塞，node的状态就是condition，返回false
                    if (node.waitStatus == Node.CONDITION || node.prev == null)
                        return false;
                    if (node.next != null) // If has successor, it must be on queue
                        return true;
                    /*
                     * node.prev can be non-null, but not yet on queue because
                     * the CAS to place it on queue can fail. So we have to
                     * traverse from tail to make sure it actually made it.  It
                     * will always be near the tail in calls to this method, and
                     * unless the CAS failed (which is unlikely), it will be
                     * there, so we hardly ever traverse much.
                     */
                    return findNodeFromTail(node);
                }

此时node的节点值为condition -2
然后，判断当前结点是不是在【等待队列】中，不在的话就会阻塞线程。
最终线程A释放了锁，并进入阻塞状态。

### 2. ThreadB获取到锁后，首先调用signal方法

由于Condition的signal方法要求线程必须获得与此Condition对象相关联的锁，所以这里有个中断判断：
     
              /**
              * Moves the longest-waiting thread, if one exists, from the
              * wait queue for this condition to the wait queue for the
              * owning lock.
              *
              * @throws IllegalMonitorStateException if {@link #isHeldExclusively}
              *         returns {@code false}
              */
             public final void signal() {
                 if (!isHeldExclusively())
                     throw new IllegalMonitorStateException();
                 Node first = firstWaiter;
                 if (first != null)
                     doSignal(first);
             }

然后，会调用doSignal方法，删除条件队列中的队首CONDITION类型结点：
        
          
                /**
                   * Removes and transfers nodes until hit non-cancelled one or
                   * null. Split out from signal in part to encourage compilers
                   * to inline the case of no waiters.
                   * @param first (non-null) the first node on condition queue
                   */
                  private void doSignal(Node first) {
                      do {
                          if ( (firstWaiter = first.nextWaiter) == null)
                              lastWaiter = null;
                          first.nextWaiter = null;
                      } while (!transferForSignal(first) &&
                               (first = firstWaiter) != null);
                  }
                  
删除完成后，transferForSignal方法会将CONDITON结点转换为初始结点，并插入【等待队列】：

        
    /**
     * Transfers a node from a condition queue onto sync queue.
     * Returns true if successful.
     * @param node the node
     * @return true if successfully transferred (else the node was
     * cancelled before signal)
     */
    final boolean transferForSignal(Node node) {
        /*
         * If cannot change waitStatus, the node has been cancelled. 初始化这个节点的值
         */
        if (!compareAndSetWaitStatus(node, Node.CONDITION, 0))
            return false;

        /*
         * Splice onto queue and try to set waitStatus of predecessor to
         * indicate that thread is (probably) waiting. If cancelled or
         * attempt to set waitStatus fails, wake up to resync (in which
         * case the waitStatus can be transiently and harmlessly wrong).
         */
         插入队列
        Node p = enq(node);
        int ws = p.waitStatus;                 node 0 -1
        if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL))
            LockSupport.unpark(node.thread);
        return true;
    }
此时，【条件队列】已经空了,firstWaiter,lastWaiter都为空
而ThreadA被包装成新结点后，插入【等待队列】即是tail，头节点是个dummy

### 3. ThreadB释放锁

终于ThreadB释放了锁，释放成功后，会调用unparkSuccessor方法（参加AQS独占功能的讲解），唤醒队列中的首结点：
    
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
这样线程A的节点的值就从-1，变成了0

## 4. ThreadA从唤醒处继续执行

ThreadA被唤醒后，从await方法的阻塞处开始继续往下执行：
    
        public final void await() throws InterruptedException {
                    if (Thread.interrupted()) 响应中断
                        throw new InterruptedException();
                    Node node = addConditionWaiter(); 添加到条件队列里
                    int savedState = fullyRelease(node); 释放锁，返回释放前的状态 第一次为1
                    int interruptMode = 0; 
                    while (!isOnSyncQueue(node)) { 如果节点不在队列里，阻塞
                        LockSupport.park(this);
                        if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                            break;
                    }
                    尝试获取锁，并对中断做处理
                    if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
                        interruptMode = REINTERRUPT;
                    if (node.nextWaiter != null) // clean up if cancelled
                        unlinkCancelledWaiters();
                    if (interruptMode != 0)
                        reportInterruptAfterWait(interruptMode);
                }

之后会调用acquireQueued方法再次尝试获取锁，获取成功后，最终等待队列状态的头和尾都指向了A线程

## 三、总结

本章以ReentrantLock的公平锁为例，分析了AbstractQueuedSynchronizer的Condition功能。
通过分析，可以看到，当线程在指定Condition对象上等待的时候，其实就是将线程包装成结点，加入了条件队列，然后阻塞。
当线程被通知唤醒时，则是将条件队列中的结点转换成等待队列中的结点，之后的处理就和独占功能完全一样。

除此之外，Condition还支持限时等待、非中断等待等功能，分析思路是一样的，读者可以自己去阅读AQS的源码，




