package jmmModule;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class VolatileTest2 {
    public static void main(String[] args) {
        VolatileTest2 volatileTest2 = new VolatileTest2();
        CyclicBarrier barrier = new CyclicBarrier(2);
        Thread one = new Thread(() -> {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            volatileTest2.writer();
        });
        Thread two = new Thread(() -> {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            volatileTest2.reader();
        });
        one.start();two.start();
    }

    private int a = 0;
    private volatile boolean flag = false;

    public void writer() {
        a = 1;
        flag = true;
    }

    public void reader() {
        if (flag) {
            int i = a;
            System.out.println(i);
        }
    }
}
