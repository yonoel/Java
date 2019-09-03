package threadSynchronizedTool;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class NumberSelector implements Function<List<Long>, Long> {
    public static void main(String[] args) {
        System.out.printf("Main :start %n");
        CompletableFuture<Integer> seedFuture = new CompletableFuture<>();
        new Thread(new SeedGenerator(seedFuture)).start();
        System.out.printf("Main :getting the seeds%n");
        int seed = 0;
        try {
            seed = seedFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: the seed is %d %n", seed);

        System.out.printf("Main: Launching the list of numbers generator%n");
        NumberListGenerator task = new NumberListGenerator(seed);
        CompletableFuture<List<Long>> startFuture = CompletableFuture.supplyAsync(task);

        System.out.printf("Main: launching step 1 %n");
        CompletableFuture<Long> step1Future = startFuture.thenApplyAsync(list -> {
            System.out.printf("%s: step 1 start %n", Thread.currentThread().getName());
            long selected = 0;
            long selectedDistance = Long.MAX_VALUE;
            long distance;
            for (Long num : list) {
                distance = Math.abs(num - 1000);
                if (distance < selectedDistance) {
                    selected = num;
                    selectedDistance = distance;
                }
            }
            System.out.printf("%s: step 1 end, result %d %n", Thread.currentThread().getName(), selected);
            return selected;
        });

        System.out.printf("Main: launching step 2 %n");
        CompletableFuture<Long> step2Future = startFuture.thenApplyAsync(list -> list.stream().max(Long::compare).get());
        CompletableFuture<Void> write2Future = step2Future.thenAccept(selected -> {
            System.out.printf("%s: step 2 end, result %d %n", Thread.currentThread().getName(), selected);

        });


        System.out.printf("Main: launching step 3 %n");
        NumberSelector numberSelector = new NumberSelector();
        CompletableFuture<Long> step3Future = startFuture.thenApplyAsync(numberSelector);

        System.out.printf("Main: Waiting for the end of the three steps%n");
        CompletableFuture<Void> waitFuture = CompletableFuture.allOf(step1Future, write2Future, step3Future);

        CompletableFuture<Void> finalFuture = waitFuture.thenAcceptAsync((param) -> {
            System.out.printf("Main: the completableFuture has done %n");
        });

        finalFuture.join();
    }

    @Override
    public Long apply(List<Long> longs) {
        System.out.printf("%s : step 3: start %n", Thread.currentThread().getName());
        Long max = longs.stream().max(Long::compare).get();
        Long min = longs.stream().min(Long::compare).get();
        long result = (max + min) / 2;
        System.out.printf("%s : step 3: end result is %d %n", Thread.currentThread().getName(), result);
        return result;
    }
}
