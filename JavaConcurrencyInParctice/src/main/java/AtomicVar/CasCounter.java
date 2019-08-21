package AtomicVar;

public class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        }
        while (v != value.compareAndSwap(v, v + 1));
        return v + 1;

    }

    private class SimulatedCAS {
        private int value2;

        public synchronized int get() {
            return value2;
        }

        public synchronized int compareAndSwap(int v, int i) {
            int oldValue = value2;
            if (oldValue == v)
                value2 = i;
            return oldValue;
        }
    }
}
