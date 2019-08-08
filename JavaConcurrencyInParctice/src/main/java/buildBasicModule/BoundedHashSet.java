package buildBasicModule;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int count) {
        set = Collections.synchronizedSet(new HashSet<T>());
        semaphore = new Semaphore(count);
    }

    public boolean add(T o) throws InterruptedException {
        semaphore.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded)
                semaphore.release();
        }
    }

    public boolean remove(Object o){
        boolean remove = set.remove(o);
        if (!remove)
            semaphore.release();
        return remove;
    }
}
