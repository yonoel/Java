package jmmModule;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class VolatileTest {
    private volatile int a;

    public VolatileTest() {
    }

    public VolatileTest(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public static void main(String[] args) throws Exception{
        VolatileTest volatileTest = new VolatileTest();
        CyclicBarrier barrier = new CyclicBarrier(2);
        Thread one = new Thread(() -> {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(volatileTest.getA());
        });
        Thread two = new Thread(() -> {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            volatileTest.setA(10);
            System.out.println(volatileTest.a);
        });
//        one.start();
        two.start();
        one.start();
        one.join();two.join();
    }
}
