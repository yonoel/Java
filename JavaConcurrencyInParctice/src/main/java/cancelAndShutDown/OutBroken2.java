package cancelAndShutDown;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OutBroken2 {
    private static final ScheduledExecutorService cancelService = Executors.newScheduledThreadPool(3);

    public static void timedRun(final Runnable runnable, long timeout, TimeUnit timeUnit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable throwable;

            @Override
            public void run() {
                try {
                    runnable.run();
                } catch (Throwable t) {
                    this.throwable = t;
                }
            }

            void rethrow() {
                if (throwable != null)
                    throw new RuntimeException(throwable);
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();

        cancelService.schedule(() -> {
            taskThread.interrupt();
        }, timeout, timeUnit);
        taskThread.join(timeUnit.toMillis(timeout));

        task.rethrow();
    }
}
