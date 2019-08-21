package taskExecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        while (true){
              final Socket accept = serverSocket.accept();
              Runnable runnable = ()->{
                  accept.getChannel();
                  // 业务
              };
              new Thread(runnable).start();
        }
    }
}
