package buildBasicModule;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Preloader {
    private final FutureTask<String> future = new FutureTask<String>(() -> {
        return "1234";
    });

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public String get() throws InterruptedException, ExecutionException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            throw (ExecutionException) cause;
        }

    }
}
