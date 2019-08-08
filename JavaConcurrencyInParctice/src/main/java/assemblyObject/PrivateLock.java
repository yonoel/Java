package assemblyObject;

import net.jcip.annotations.GuardedBy;

public class PrivateLock {
    private final Object lock = new Object();
    @GuardedBy("this")
    PersonSet personSet;

    void doSomething(){
        synchronized (lock){
            //....
        }
    }
}
