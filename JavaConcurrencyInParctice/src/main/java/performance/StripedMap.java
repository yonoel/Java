package performance;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class StripedMap {
    private static final int N_LOCKS = 16;
    private final Node[] buckets;
    private final Object[] locks;

    class Node {
        Object value;
        Node next;
        Object key;
    }

    public StripedMap(int numBuckets) {
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        for (int i = 0; i < N_LOCKS; i++) {
            locks[i] = new Object();
        }
    }

    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public Object get(Object key) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (Node n = buckets[hash]; n != null; n = n.next) {
                if (n.key.equals(key))
                    return n.value;
            }
        }
        return null;
    }
}
