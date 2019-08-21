package concurrentTest;

import org.junit.Test;

public class Big {
    double[] data = new double[100000];

    @Test
    public void testLeak() throws InterruptedException {
        BoundBuffer<Big> buffer = new BoundBuffer<>(10);
        long memory = Runtime.getRuntime().totalMemory();
        for (int i = 0; i < 10; i++) {
            buffer.put(new Big());
        }
        for (int i = 0; i < 10; i++) {
            buffer.take();
        }
        long memory1 = Runtime.getRuntime().totalMemory();
        System.out.println(memory - memory1);
    }
}
