package threadExecutor;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ReportProcessor implements Runnable {
    private volatile boolean end;
    private final CompletionService<String> service;

    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        end = false;
    }

    @Override
    public void run() {
        while (!end) {
            try {
                Future<String> future = service.poll(20, TimeUnit.SECONDS);
                if (future != null) {
                    String s = future.get();
                    System.out.printf("ReportReceiver: report received %s%n", s);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.end = true;
    }
}
