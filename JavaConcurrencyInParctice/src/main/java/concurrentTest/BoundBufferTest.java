package concurrentTest;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoundBufferTest extends Thread {
    @Test
    public void testIsEmptyWhenConstructed() {
        BoundBuffer<Integer> buffer = new BoundBuffer<>(10);
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }

    @Test
    public void testIsFullAfterPuts() throws InterruptedException {
        BoundBuffer<Integer> buffer = new BoundBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            buffer.put(i);
        }
        assertTrue(buffer.isFull());
        assertFalse(buffer.isEmpty());
    }

    @Test
    public void testTakeBlocksWhenIsEmpty() {
        BoundBuffer<Integer> buffer = new BoundBuffer<>(10);
        Thread taker = new Thread() {
            @Override
            public void run() {
                try {
                    buffer.take();
                    fail();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        try {
            taker.start();
            Thread.sleep(10);
            taker.interrupt();
            taker.join(10);
            assertFalse(taker.isAlive());
        } catch (Exception e) {
            fail();
        }
    }
}
