package threadSynchronizedTool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class SeedGenerator implements Runnable {
    private CompletableFuture<Integer> resultCommunicator;

    public SeedGenerator(CompletableFuture<Integer> resultCommunicator) {
        this.resultCommunicator = resultCommunicator;
    }

    @Override
    public void run() {
        System.out.printf("SeedGenerator:Generator seed......%n");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int rint = (int) Math.rint(Math.random() * 10);

        System.out.printf("SeedGenerator:seed generated %d %n", rint);
        resultCommunicator.complete(rint);
    }
}
