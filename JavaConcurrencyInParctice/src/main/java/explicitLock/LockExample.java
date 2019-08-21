package explicitLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    public void example(){
        Lock lock = new ReentrantLock();
        // ...
        lock.lock();
        try {
            // ...
        }finally {
            lock.unlock();
        }
    }
}
