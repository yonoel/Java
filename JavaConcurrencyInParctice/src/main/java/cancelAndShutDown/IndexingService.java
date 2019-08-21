package cancelAndShutDown;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class IndexingService {
    private static final File POISON = new File("");
    private final BlockingQueue<File> queue;
    private final File root;
    private final FileFilter fileFilter;
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }

    public IndexingService(File root, FileFilter fileFilter) {
        this.root = root;
        this.fileFilter = fileFilter;
        queue = new LinkedBlockingQueue<>();
    }

    public class CrawlerThread extends Thread {
        @Override
        public void run() {
            try {
                crawl(root);
            } catch (Exception e) {
                // handle e
                e.printStackTrace();
            } finally {
                while (true) {
                    try {
                        queue.put(POISON);
                        break;
                    } catch (InterruptedException e) {
                        // retry
                    }
                }
            }
        }

        private void crawl(File root) {
        }
    }

    public class IndexerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    if (file == POISON) {
                        break;
                    } else
                        indexFile(file);
                }
            } catch (InterruptedException e) {
            }
        }

        private void indexFile(File file) {
        }
    }

}
