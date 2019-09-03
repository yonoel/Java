package threadSynchronizedTool;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class TestBarrier {
    public static void main(String[] args) {
        int parties = 3;
        CyclicBarrier barrier = new CyclicBarrier(parties);
        Runnable runnable = () -> {
            try {
                Random random = new Random();
                TimeUnit.SECONDS.sleep(random.nextInt(10));
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.printf("%s:休眠结束 %n", Thread.currentThread().getName());
        };
        new Thread(runnable, "test1").start();
        new Thread(runnable, "test2").start();
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: has done");
    }
}
