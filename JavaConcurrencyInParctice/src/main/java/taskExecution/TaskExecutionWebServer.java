package taskExecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutionWebServer {
    private final static int length_thread = 100;
    private final static Executor executor =
            Executors.newFixedThreadPool(length_thread);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        while (true){
            final Socket accept = serverSocket.accept();
            Runnable runnable = ()->{
                accept.getChannel();
                // 业务
            };
            executor.execute(runnable);
        }
    }
}
