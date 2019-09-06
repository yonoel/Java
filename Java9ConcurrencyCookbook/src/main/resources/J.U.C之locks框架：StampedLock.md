# 一、StampedLock类简介

StampedLock类，在JDK1.8时引入，是对读写锁ReentrantReadWriteLock的增强，该类提供了一些功能，优化了读锁、写锁的访问，同时使读写锁之间可以互相转换，更细粒度控制并发。
  
首先明确下，该类的设计初衷是作为一个内部工具类，用于辅助开发其它线程安全组件，用得好，该类可以提升系统性能，用不好，容易产生死锁和其它莫名其妙的问题。

### 1.1 StampedLock的引入

先来看下，为什么有了ReentrantReadWriteLock，还要引入StampedLock？
ReentrantReadWriteLock使得多个读线程同时持有读锁（只要写锁未被占用），而写锁是独占的。

但是，读写锁如果使用不当，很容易产生“饥饿”问题：

比如在读线程非常多，写线程很少的情况下，很容易导致写线程“饥饿”，
虽然使用“公平”策略可以一定程度上缓解这个问题，但是“公平”策略是以牺牲系统吞吐量为代价的。（在ReentrantLock类的介绍章节中，介绍过这种情况）

### 1.2 StampedLock的特点

StampedLock的主要特点概括一下，有以下几点：

+   所有获取锁的方法，都返回一个邮戳（Stamp），Stamp为0表示获取失败，其余都表示成功；
+   所有释放锁的方法，都需要一个邮戳（Stamp），这个Stamp必须是和成功获取锁时得到的Stamp一致；
+   StampedLock是不可重入的；（如果一个线程已经持有了写锁，再去获取写锁的话就会造成死锁）

StampedLock有三种访问模式：
+   Reading（读模式）：功能和ReentrantReadWriteLock的读锁类似
+   Writing（写模式）：功能和ReentrantReadWriteLock的写锁类似
+   Optimistic reading（乐观读模式）：这是一种优化的读模式。

StampedLock支持读锁和写锁的相互转换
我们知道RRW中，当线程获取到写锁后，可以降级为读锁，但是读锁是不能直接升级为写锁的。StampedLock提供了读锁和写锁相互转换的功能，使得该类支持更多的应用场景。
无论写锁还是读锁，都不支持Conditon等待.

我们知道，在ReentrantReadWriteLock中，当读锁被使用时，如果有线程尝试获取写锁，该写线程会阻塞。
但是，在Optimistic reading中，即使读线程获取到了读锁，写线程尝试获取写锁也不会阻塞，这相当于对读模式的优化，
但是可能会导致数据不一致的问题。所以，当使用Optimistic reading获取到读锁时，必须对获取结果进行校验.

## 二、StampedLock使用示例

    long stamp = lock.tryOptimisticRead();  // 非阻塞获取版本信息
    copyVaraibale2ThreadMemory();           // 拷贝变量到线程本地堆栈
    if(!lock.validate(stamp)){              // 校验
        long stamp = lock.readLock();       // 获取读锁
        try {
            copyVaraibale2ThreadMemory();   // 拷贝变量到线程本地堆栈
         } finally {
           lock.unlock(stamp);              // 释放悲观锁
        }
    
    }
    useThreadMemoryVarables();              // 使用线程本地堆栈里面的数据进行操作

## 三、StampedLock原理

3.1 StampedLock的内部常量
StampedLock虽然不像其它锁一样定义了内部类来实现AQS框架，但是StampedLock的基本实现思路还是利用CLH队列进行线程的管理，通过同步状态值来表示锁的状态和类型。

StampedLock内部定义了很多常量，定义这些常量的根本目的还是和ReentrantReadWriteLock一样，对同步状态值按位切分，以通过位运算对State进行操作：

对于StampedLock来说，写锁被占用的标志是第8位为1，读锁使用0-7位，正常情况下读锁数目为1-126，超过126时，使用一个名为readerOverflow的int整型保存超出数。

另外，StampedLock相比ReentrantReadWriteLock，对多核CPU进行了优化，可以看到，当CPU核数超过1时，会有一些自旋操作:


