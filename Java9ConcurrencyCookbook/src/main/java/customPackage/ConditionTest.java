package customPackage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    public static void main(String[] args) throws Exception {
        ReentrantLock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        Runnable c_r = ()->{
          lock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();

        };
        Runnable a_r = () -> {
            lock.lock();
            System.out.printf("%s上锁%n", Thread.currentThread().getName());
            try {
                System.out.printf("阻塞线程，释放锁%n");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
//                lock.unlock();
                System.out.printf("%s解锁%n", Thread.currentThread().getName());
            }
        };

        Runnable b_r = () -> {
            lock.lock();
            System.out.printf("%s上锁%n", Thread.currentThread().getName());
//            condition.signal();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.printf("%s解锁%n", Thread.currentThread().getName());
        };


        Thread a = new Thread(a_r);
//        a.start();
//        TimeUnit.SECONDS.sleep(1);

        Thread b = new Thread(b_r);
        Thread c_t = new Thread(c_r);
        c_t.start();
        b.start();

        TimeUnit.SECONDS.sleep(1);
        a.start();

        a.join();
        b.join();
        c_t.join();
    }


}
