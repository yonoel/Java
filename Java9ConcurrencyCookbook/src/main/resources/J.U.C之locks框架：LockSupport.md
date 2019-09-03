# J.U.C之locks框架：LockSupport

## 一、LockSupport类简介

LockSupport类，是JUC包中的一个工具类，是用来创建锁和其他同步类的基本线程阻塞原语。（Basic thread blocking primitives for creating locks and other synchronization classes）

LockSupport类的核心方法其实就两个：park()和unark()，其中park()方法用来阻塞当前调用线程，unpark()方法用于唤醒指定线程。
这其实和Object类的wait()和signial()方法有些类似，但是LockSupport的这两种方法从语意上讲比Object类的方法更清晰，而且可以针对指定线程进行阻塞和唤醒。

LockSupport类使用了一种名为Permit（许可）的概念来做到阻塞和唤醒线程的功能，可以把许可看成是一种(0,1)信号量（Semaphore），
但与 Semaphore 不同的是，许可的累加上限是1。初始时，permit为0，当调用unpark()方法时，线程的permit加1，当调用park()方法时，如果permit为0，则调用线程进入阻塞状态。

通过LockSupport的这两个方法，可以很方便的阻塞和唤醒线程。但是LockSupport的使用过程中还需要注意以下几点：

+   park方法的调用一般要方法一个循环判断体里面。
之所以这样做，是为了防止线程被唤醒后，不进行判断而意外继续向下执行，这其实是一种Guarded Suspension的多线程设计模式。

+   park方法是会响应中断的，但是不会抛出异常。(也就是说如果当前调用线程被中断，则会立即返回但不会抛出中断异常)
+   park的重载方法park(Object blocker)，会传入一个blocker对象，所谓Blocker对象，其实就是当前线程调用时所在调用对象（如上述示例中的FIFOMutex对象）。
该对象一般供监视、诊断工具确定线程受阻塞的原因时使用。

## 二、LockSupport类/方法声明

都是静态方法，相当于提供了一个阻塞工具

