package threadManagement;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable {
    public static void main(String[] args) {
        FileSearch search = new FileSearch("/Users","build.gradle");
        Thread thread = new Thread(search);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
    private String initPath;
    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(fileName);
        if (file.isDirectory()) {
            try {
                directoryProcess(file);
            } catch (InterruptedException ex) {
                System.out.printf(" %s the search has been interrupted ", Thread.currentThread().getName());
            }
        }
    }

    private void directoryProcess(File file) throws InterruptedException {
        File[] files = file.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File file1 = files[i];
                if (file1.isDirectory()) directoryProcess(file1);
                else fileProcess(file1);
            }
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

    private void fileProcess(File file) throws InterruptedException{
        if (file.getName().equals(fileName)){
            System.out.printf("%s %s %n",Thread.currentThread().getName(),file.getAbsolutePath());
        }
        if (Thread.interrupted()){
            throw new InterruptedException();
        }
    }
}
