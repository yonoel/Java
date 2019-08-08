package buildBasicModule;

import java.io.File;
import java.io.FileFilter;
import java.util.IdentityHashMap;
import java.util.concurrent.BlockingQueue;

public class FileCrawler implements Runnable{
    private final File root;
    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;

    public FileCrawler(File root, BlockingQueue<File> fileQueue, FileFilter fileFilter) {
        this.root = root;
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException{
        File[] files = root.listFiles(fileFilter);
        if (files != null){
            for (File file:files)
                if (file.isDirectory())
                    crawl(file);
                else if (!alreadyIndexed(file))
                    fileQueue.put(file);
        }
    }

    private boolean alreadyIndexed(File file) {
        return fileFilter.accept(file);
    }
}
