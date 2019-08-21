package performance;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestList {
    private static final int N_THREADS = 3;

    public static void main(String[] args) {
        ExecutorService inService = Executors.newFixedThreadPool(N_THREADS);
        ExecutorService outService = Executors.newFixedThreadPool(N_THREADS);
        List<Runnable> synchronizedList = Collections.synchronizedList(new LinkedList<Runnable>());
        ConcurrentLinkedQueue<Runnable> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();


    }
}
