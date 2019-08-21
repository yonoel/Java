package buildCustomConcurrentTool;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class GrumpyBoundedBuffer<V> extends BaseBoundBuffer<V> {
    public static void main(String[] args) {
        GrumpyBoundedBuffer<Integer> buffer = new GrumpyBoundedBuffer<>(5);
        while (true){
            try {
                Integer take = buffer.take();
            } catch (IllegalArgumentException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public GrumpyBoundedBuffer(int c) {
        super(c);
    }

    public synchronized void put(V v) throws IllegalArgumentException {
        if (isFull())
            throw new IllegalArgumentException("is full");
        doPut(v);
    }

    public synchronized V take() throws IllegalArgumentException{
        if (isEmpty())
            throw new IllegalArgumentException("is empty");
        return doTake();
    }
}
