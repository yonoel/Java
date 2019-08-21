package cancelAndShutDown;

import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class OneCheckMail {
    public boolean checkMail(Set<String> hosts, long timeout, TimeUnit timeUnit)
    throws InterruptedException{
        ExecutorService service = Executors.newCachedThreadPool();
        final AtomicBoolean hasNewMail = new AtomicBoolean(false);
        try {
            for (final String host : hosts) {
                service.execute(() -> {
                    if (checkMail(host))
                        hasNewMail.set(true);
                });
            }
        } finally {
            service.shutdown();
            service.awaitTermination(timeout,timeUnit);
        }

        return hasNewMail.get();
    }

    private boolean checkMail(String host) {
        return false;
    }
}
