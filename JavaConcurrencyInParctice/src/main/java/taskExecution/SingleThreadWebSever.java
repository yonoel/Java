package taskExecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadWebSever {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        while (true)
        {
            Socket accept = serverSocket.accept();
            // services
        }

    }
}
