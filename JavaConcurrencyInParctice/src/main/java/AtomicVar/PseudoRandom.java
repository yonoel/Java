package AtomicVar;

public class PseudoRandom {
    public int calculateNext(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;

    }
}
