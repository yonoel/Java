package customPackage;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    public static void main(String[] args) throws Exception{
        CountDownLatch latch = new CountDownLatch(1);
        Runnable runnable = ()->{
            try {
                System.out.println("阻塞");
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread a = new Thread(runnable);
        Thread b = new Thread(runnable);
        a.start();
        b.start();

        TimeUnit.SECONDS.sleep(1);
        latch.countDown();
        a.join();
        b.join();

    }
}
