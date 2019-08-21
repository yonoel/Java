package cancelAndShutDown;

import net.jcip.annotations.GuardedBy;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogService {
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;
    private final PrintWriter writer;
    @GuardedBy("this")
    private boolean isShutdown;
    @GuardedBy("this")
    private int reservations;

    public LogService(PrintWriter writer) {
        this.writer = writer;
        queue = new LinkedBlockingQueue<>();
        logger = new LoggerThread();
    }

    public void start() {
        logger.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        logger.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown)
                throw new IllegalArgumentException();
            ++reservations;
        }
    }

    private class LoggerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (LogService.this) {
                            if (isShutdown && reservations == 0)
                                break;
                            String take = queue.take();
                            synchronized (LogService.this) {
                                --reservations;
                            }
                            writer.println(take);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // retry
                    }
                }
            } finally {
                writer.close();
            }
        }
    }

}

