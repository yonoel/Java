package concurrentTest;

public class XorShiftClass {
    public static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y <<7);
        return y;
    }
}
