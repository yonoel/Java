package AtomicVar;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockPseudoRandom extends PseudoRandom {
    protected final ReentrantLock lock = new ReentrantLock();
    private int seed;

    public ReentrantLockPseudoRandom(int seed) {
        this.seed = seed;
    }

    public int nextInt(int i) {
        lock.lock();
        try {
            int s = seed;
            seed = calculateNext(s);
            int remainder = s % i;
            return remainder > 0 ? remainder : remainder + i;
        }finally {
            lock.unlock();
        }

    }
}
