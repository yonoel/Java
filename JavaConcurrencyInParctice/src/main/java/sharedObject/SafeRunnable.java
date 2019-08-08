package sharedObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SafeRunnable {
    private final Runnable runnable;

    private SafeRunnable() {
        runnable = () -> {
            System.out.println(111);
        };
    }

    public static SafeRunnable newInstance(ExecutorService service) {
        SafeRunnable runnable = new SafeRunnable();
        service.execute(runnable.runnable);
        return runnable;
    }
}
