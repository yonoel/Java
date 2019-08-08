package MultiThread;

import java.util.concurrent.*;

public class ThreadSafeTest1 {

    private static int[] martix = new int[100];

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(100);
//        CountDownLatch downLatch = new CountDownLatch(100);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                try {
//                    downLatch.await();
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(getX());
            });
//            downLatch.countDown();
        }
        executorService.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("value is" + getMartix());

    }

    private synchronized static int getX() {
        return martix[50]++;
    }

    private synchronized static int getMartix() {
        return martix[50];
    }
}
