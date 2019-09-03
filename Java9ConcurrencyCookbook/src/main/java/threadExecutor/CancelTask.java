package threadExecutor;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CancelTask {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> submit = executor.submit(() -> {
            try {
                System.out.println("starting...." + new Date());
                TimeUnit.SECONDS.sleep(1);
                System.out.println("ending..." + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            TimeUnit.SECONDS.sleep(1);
            boolean cancel = submit.cancel(true);
            System.out.println(cancel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
