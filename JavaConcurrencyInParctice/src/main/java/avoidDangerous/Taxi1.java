package avoidDangerous;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.awt.*;

@ThreadSafe
public class Taxi1 {
    @GuardedBy("this")
    private Point location, destination;
    private final Dispatcher1 dispatcher;

    public Taxi1(Dispatcher1 dispatcher) {
        this.dispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        boolean reachedDestination;
        synchronized (this){
            this.location = location;
            reachedDestination = location.equals(destination);
        }
        if (reachedDestination)
            dispatcher.notifyAvailable(this);
    }
}
