# J.U.C之locks框架：AQS综述(1)

## AQS简介
AbstractQueuedSynchronizer抽象类（以下简称AQS）是整个java.util.concurrent包的核心。
在JDK1.5时，Doug Lea引入了J.U.C包，该包中的大多数同步器都是基于AQS来构建的。
AQS框架提供了一套通用的机制来管理同步状态（synchronization state）、阻塞/唤醒线程、管理等待队列。
  
我们所熟知的ReentrantLock、CountDownLatch、CyclicBarrier等同步器，其实都是通过内部类实现了AQS框架暴露的API，以此实现各类同步器功能。
这些同步器的主要区别其实就是对同步状态（synchronization state）的定义不同。
  
AQS框架，分离了构建同步器时的一系列关注点，它的所有操作都围绕着资源——同步状态（synchronization state）来展开，并替用户解决了如下问题：
  
+   资源是可以被同时访问？还是在同一时间只能被一个线程访问？（共享/独占功能）
+   访问资源的线程如何进行并发管理？（等待队列）
+   如果线程等不及资源了，如何从等待队列退出？（超时/中断）

这其实是一种典型的模板方法设计模式：父类（AQS框架）定义好骨架和内部操作细节，具体规则由子类去实现。

AQS框架将剩下的一个问题留给用户：什么是资源？如何定义资源是否可以被访问？

我们来看下几个常见的同步器对这一问题的定义：

    同步器	        资源的定义
    ReentrantLock	资源表示独占锁。State为0表示锁可用；为1表示被占用；为N表示重入的次数
    CountDownLatch	资源表示倒数计数器。State为0表示计数器归零，所有线程都可以访问资源；为N表示计数器未归零，所有线程都需要阻塞。
    Semaphore	    资源表示信号量或者令牌。State≤0表示没有令牌可用，所有线程都需要阻塞；大于0表示由令牌可用，线程每获取一个令牌，State减1，线程没释放一个令牌，State加1。
    ReentrantReadWriteLock	资源表示共享的读锁和独占的写锁。state逻辑上被分成两个16位的unsigned short，分别记录读锁被多少线程使用和写锁被重入的次数。
    
综上所述，AQS框架提供了以下功能：

+ 提供一套模板框架
由于并发的存在，需要考虑的情况非常多，因此能否以一种相对简单的方法来完成这两个目标就非常重要，
因为对于用户（AQS框架的使用者来说），很多时候并不关心内部复杂的细节。
而AQS其实就是利用模板方法模式来实现这一点，AQS中大多数方法都是final或是private的，也就是说Doug Lea并不希望用户直接使用这些方法，而是只覆写部分模板规定的方法。
AQS通过暴露以下API来让让用户自己解决上面提到的“如何定义资源是否可以被访问”的问题：

        钩子方法	            描述
        tryAcquire	        排它获取（资源数）
        tryRelease	        排它释放（资源数）
        tryAcquireShared	共享获取（资源数）
        tryReleaseShared	共享获取（资源数）
        isHeldExclusively	是否排它状态

