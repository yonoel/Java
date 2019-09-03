package threadExecutor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledTask {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
/*
        for (int i = 0; i < 5; i++) {
            int i1 = i;
            service.schedule(() -> {
                System.out.println(" i am task" + i1);
            },i+1, TimeUnit.SECONDS);
        }*/
        ScheduledFuture<?> scheduledFuture = service.scheduleAtFixedRate(() -> {
            System.out.println("i m task " + new Date());
        }, 1, 2, TimeUnit.SECONDS);
        for (int i = 0; i < 10; i++) {
            System.out.printf("Main delay %d %n", scheduledFuture.getDelay(TimeUnit.MILLISECONDS));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 会关闭周期任务
        service.shutdown();

        try {
//            service.awaitTermination(1, TimeUnit.DAYS);
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main done %s%n", new Date());
    }
}
