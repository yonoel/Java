package forkJoinTool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DocumentTask extends RecursiveTask<Integer> {
    public static void main(String[] args) {
        DocumentMock mock = new DocumentMock();
        String[][] mocked = mock.generateDocument(100, 1000, "the");
        DocumentTask documentTask = new DocumentTask(mocked, 0, mocked.length, "the");

        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.execute(documentTask);
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
        } while (!documentTask.isDone());
        pool.shutdown();
        try {
            pool.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.printf("Main: the word appears %d in the document",documentTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    private String document[][];
    private int start, end;
    private String word;

    public DocumentTask(String[][] document, int start, int end, String word) {
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        Integer result = null;
        if (end - start < 10) {
            result = processLines(document, start, end, word);
        } else {
            int mid = (start + end) / 2;
            DocumentTask task1 = new DocumentTask(document, start, mid + 1, word);
            DocumentTask task2 = new DocumentTask(document, mid + 1, end, word);
            invokeAll(task1, task2);
            try {
                result = groupResults(task1.get(), task2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private Integer groupResults(Integer result1, Integer result2) {
        return result1 + result2;
    }

    private Integer processLines(String[][] document, int start, int end, String word) {
        List<LineTask> tasks = new ArrayList<>();
        for (int i = start; i < end; i++) {
            LineTask lineTask = new LineTask(document[i], 0, document[i].length, word);
            tasks.add(lineTask);
        }
        invokeAll(tasks);
        int result = 0;
        for (int i = 0; i < tasks.size(); i++) {
            LineTask task = tasks.get(i);
            try {
                result += task.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
