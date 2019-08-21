package buildCustomConcurrentTool;

import net.jcip.annotations.GuardedBy;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class ThreadGate {
    @GuardedBy("this")
    private boolean isOpen;
    @GuardedBy("this")
    private int generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = this.generation;
        while (!isOpen && arrivalGeneration == generation)
            wait();
    }
}
