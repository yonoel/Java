package forkJoinTool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class FolderProcessor extends CountedCompleter<List<String>> {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        FolderProcessor java = new FolderProcessor("/Users/yonoel/Desktop/java", "java");
//        FolderProcessor python = new FolderProcessor("/Users/yonoel/Desktop/Python", "log");

        pool.execute(java);
//        pool.execute(python);
        do {
            System.out.println("*************************************");
            System.out.printf("Main:active thread count %d %n", pool.getActiveThreadCount());
            System.out.printf("Main:task count %d %n", pool.getQueuedTaskCount());
            System.out.printf("Main:steal count  %d %n", pool.getStealCount());
            System.out.println("*************************************");
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!java.isDone()
//                || !python.isDone()
        );

        pool.shutdown();
        List<String> join = null;
        try {
            join = java.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        List<String> join = java.join();
        System.out.println(join.size());
//        System.out.println(join.size());
    }

    private String path;
    private String extension;
    private List<FolderProcessor> tasks;
    private List<String> resultList;

    protected FolderProcessor(CountedCompleter<?> completer, String path, String extension) {
        super(completer);
        this.path = path;
        this.extension = extension;
    }

    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Override
    public void compute() {
        resultList = new ArrayList<>();
        tasks = new ArrayList<>();
        File file = new File(path);
        File[] contents = file.listFiles();

        if (contents != null) {
            for (int i = 0; i < contents.length; i++) {
                File content = contents[i];
                if (content.isDirectory()) {
                    FolderProcessor task = new FolderProcessor(this, content.getAbsolutePath(), extension);
                    task.fork();
                    addToPendingCount(1);
                    tasks.add(task);
                } else {
                    if (checkFile(content.getName())) {
                        resultList.add(content.getAbsolutePath());
                    }
                }
            }
            if (tasks.size() > 50) {
                System.out.printf("%s: %d tasks ran %n", file.getAbsolutePath(), tasks.size());
            }
        }
        tryComplete();
    }


    @Override
    public List<String> getRawResult() {
        return getResultList();
    }

    @Override
    public void onCompletion(CountedCompleter<?> caller) {
        for (FolderProcessor childTask : tasks) {
            resultList.addAll(childTask.getResultList());
        }
    }

    public List<String> getResultList() {
        return resultList;
    }

    private boolean checkFile(String name) {
        return name.endsWith(extension);
    }
}
