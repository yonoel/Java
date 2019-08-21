package cancelAndShutDown;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class BrokenPrimeGenerator extends Thread {
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    public BrokenPrimeGenerator(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {

            BigInteger one = BigInteger.ONE;
            while (!cancelled) {
//          while (!Thread.currentThread().isInterrupted())
                queue.put(one = one.nextProbablePrime());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public void cancel() {
        cancelled = true;
//        interrupt();
    }

    void ConsumerPrimes() throws InterruptedException {
        BlockingQueue<BigInteger> queue = new LinkedBlockingQueue<>(200);
        BrokenPrimeGenerator generator = new BrokenPrimeGenerator(queue);
        generator.start();
        try {
            while (needMorePrimes()) {
                consume(queue.take());
            }
        } finally {
            generator.cancel();
        }

    }

    private void consume(BigInteger take) {

    }

    private boolean needMorePrimes() {
        return false;
    }
}
