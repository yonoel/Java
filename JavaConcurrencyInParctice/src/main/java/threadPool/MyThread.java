package threadPool;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThread extends Thread {
    public static final String DEFAULT_NAME = "MyAppThread";
    private static volatile boolean debugLifecycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger logger = Logger.getAnonymousLogger();

    public MyThread(Runnable target) {
        super(target, DEFAULT_NAME);
    }

    public MyThread(Runnable target, String name) {
        super(target, name + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler((thread, throwable) -> {
                    logger.log(Level.SEVERE, "uncaughtException in thread" + thread.getName(), throwable);
                }
        );
    }

    @Override
    public void run() {
        boolean debugLifecycle = MyThread.debugLifecycle;
        if (debugLifecycle) logger.log(Level.FINE,"created:"+getName());
        try {
            alive.incrementAndGet();
            super.run();
        }finally {
            alive.decrementAndGet();
            if (debugLifecycle) logger.log(Level.FINE, "exiting:" + getName());
        }
    }

    public static boolean isDebugLifecycle() {
        return debugLifecycle;
    }

    public static int getCreated() {
        return created.get();
    }

    public static int getAlive() {
        return alive.get();
    }

    public static void setDebugLifecycle(boolean debugLifecycle) {
        MyThread.debugLifecycle = debugLifecycle;
    }
}
