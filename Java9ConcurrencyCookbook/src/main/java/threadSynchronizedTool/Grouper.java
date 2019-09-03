package threadSynchronizedTool;

import java.util.concurrent.CyclicBarrier;

public class Grouper implements Runnable {
    public static void main(String[] args) {
        final int rows = 10000;
        final int numbers = 1000;
        final int search = 5;
        final int participant = 5;
        final int line_participant = 2000;
        MatrixMock matrixMock = new MatrixMock(rows, numbers, search);
        Results results = new Results(rows);
        Grouper grouper = new Grouper(results);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(participant, grouper);

        for (int i = 0; i < participant; i++) {
            new Thread(new Searcher(i * line_participant, (i * line_participant) + line_participant, matrixMock, results, 5, cyclicBarrier)).start();
        }
    }

    private final Results results;

    public Grouper(Results results) {
        this.results = results;
    }

    @Override
    public void run() {
        int finalResult = 0;
        System.out.printf("Grouper: processing results......%n");
        int data[] = results.getData();
        for (int num : data) {
            finalResult += num;
        }
        System.out.printf("Grouper: total result %d %n", finalResult);
    }
}
