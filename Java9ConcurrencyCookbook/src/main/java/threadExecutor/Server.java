package threadExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        System.out.printf("Main: starting %n");
        for (int i = 0; i < 100; i++) {
            Task task = new Task("task" + i);
            server.executeTask(task);
        }
        System.out.printf("Main: shutting down server%n");
        server.endServer();
        System.out.printf("Main: sending another task to server%n");
        Task rejected_task = new Task("rejected Task");
        server.executeTask(rejected_task);

        System.out.printf("Main:end.%n");
    }

    private final ThreadPoolExecutor executor;

    public Server() {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        RejectedTaskController rejectedTaskController = new RejectedTaskController();
        executor.setRejectedExecutionHandler(rejectedTaskController);
    }

    public void executeTask(Task task) {
        System.out.printf("Server: A new task has arrived %n");
        executor.execute(task);
        System.out.printf("Server: Pool size: %d %n", executor.getPoolSize());
        System.out.printf("Server: active size: %d %n", executor.getActiveCount());
        System.out.printf("Server: task size: %d %n", executor.getTaskCount());
        System.out.printf("Server: completed size: %d %n", executor.getCompletedTaskCount());
    }

    public void endServer() {
        executor.shutdown();
    }
}
