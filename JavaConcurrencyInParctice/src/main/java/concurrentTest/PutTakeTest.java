package concurrentTest;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class PutTakeTest {
    public static void main(String[] args) throws InterruptedException {
//        new PutTakeTest(10, 10, 1000000).test();
//        pool.shutdown();
        int tpt = 100000;// 每个线程的测试次数
        for (int cap = 1; cap <= 1000; cap *= 10) {
            System.out.println("capacity is " + cap);
            for (int pairs = 1; pairs <= 128; pairs *= 2) {
                PutTakeTest takeTest = new PutTakeTest(cap, pairs, tpt);
                System.out.println("Pairs is " + pairs);
                takeTest.test();
                System.out.print("\t");
                Thread.sleep(1000);
                takeTest.test();
                System.out.println();
                Thread.sleep(1000);
            }
        }
        pool.shutdown();
    }

    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);
    private final CyclicBarrier barrier;
    private final BoundBuffer<Integer> buffer;
    private final int nTrials, nPairs;
    private final BarrierTimer timer;

    public PutTakeTest(int c, int nPairs, int nTrials) {
        this.buffer = new BoundBuffer<>(c);
        this.nPairs = nPairs;
        this.nTrials = nTrials;
        this.barrier = new CyclicBarrier(nPairs * 2 + 1);
        this.timer = new BarrierTimer();
    }

    public void test() {
        try {

            for (int i = 0; i < nPairs; i++) {
                // 开了 2 * nPairs个线程
                pool.execute(new Producer());
                pool.execute(new Consumer());
            }
            // 这是最后那一个线程，也就是主线程计数
            barrier.await(); // 阻塞线程一起开始
            barrier.await(); // 等待线程结束
            long nsPerItem = timer.getTime() / (nPairs * (long)nTrials);
            System.out.println("每个任务执行" + nsPerItem);
            assertEquals(putSum.get(), takeSum.get());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private class Producer implements Runnable {
        @Override
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int) System.nanoTime());
                int sum = 0;
                barrier.await();
                for (int i = nTrials; i > 0; --i) {
                    buffer.put(seed);
                    sum += seed;
                    seed = XorShiftClass.xorShift(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                barrier.await();
                int sum = 0;
                for (int i = nTrials; i > 0; --i) {
                    sum += buffer.take();
                }
                takeSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class BarrierTimer implements Runnable {
        private boolean started;
        private long startTime, endTime;

        @Override
        public synchronized void run() {
            long l = System.nanoTime();
            if (!started) {
                started = true;
                startTime = l;
            } else endTime = l;
        }

        public synchronized void clear() {
            started = false;
        }

        public synchronized long getTime() {
            return endTime - startTime;
        }
    }
}
