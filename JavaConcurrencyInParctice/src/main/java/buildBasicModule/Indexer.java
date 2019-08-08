package buildBasicModule;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Indexer implements Runnable {
    private BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (;;)
                indexFile(queue.take());
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File take) {
        // 从队列中取出文件名称重新建立索引
    }
}
