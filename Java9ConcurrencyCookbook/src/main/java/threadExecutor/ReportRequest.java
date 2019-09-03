package threadExecutor;

import java.io.File;
import java.util.concurrent.*;

public class ReportRequest implements Runnable {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        ReportRequest face = new ReportRequest("Face", completionService);
        ReportRequest online = new ReportRequest("online", completionService);

        ReportProcessor processor = new ReportProcessor(completionService);

        System.out.printf("Starting all");

        Thread face_thread = new Thread(face);
        Thread online_thread = new Thread(online);
        Thread pro_thread = new Thread(processor);
        face_thread.start();
        pro_thread.start();
        online_thread.start();


        try {
            pro_thread.join();
            face_thread.join();
            online_thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        processor.stop();
        System.out.println("end all");

    }

    private final String name;
    private final CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        ReportGenerator report = new ReportGenerator(name, "Report ");
        service.submit(report);
    }
}
