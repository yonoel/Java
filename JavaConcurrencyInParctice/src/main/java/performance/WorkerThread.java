package performance;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread {


    private final BlockingQueue<Runnable> queue;

    public WorkerThread(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.take().run();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;// 允许退出
            }
        }
    }
}
