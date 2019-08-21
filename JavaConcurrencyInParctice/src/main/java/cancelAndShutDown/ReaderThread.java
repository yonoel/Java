package cancelAndShutDown;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ReaderThread extends Thread {
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException i) {
            // ignore
        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] bytes = new byte[1024];
            while (true){
                int read = in.read(bytes);
                if (read < 0)
                    break;
                else if (read > 0){
                    // 处理输入
                }
            }
        }catch (IOException e){
            // 线程退出
        }
    }
}
