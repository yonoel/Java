package MultiThread;

import java.util.concurrent.CountDownLatch;

public class TestCountDown {
    public static void main(String[] args) {
        System.out.println("main thread start");
        final CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("sleep exception");
                }
                System.out.println(" i'm " + latch.getCount() + "thread");
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end");
    }
}
