package assemblyObject;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import sharedObject.MutableInteger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class MonitorVehicleTracker {
    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint mutablePoint = locations.get(id);
        return mutablePoint == null ? null : new MutablePoint(mutablePoint);
    }

    public synchronized void setLocation(String id,int x ,int y){
        MutablePoint point = locations.get(id);
        if (point == null)
            throw new IllegalArgumentException("no such id:"+id);
        point.x = x;
        point.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> locations) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (String id : locations.keySet())
            result.put(id, new MutablePoint(locations.get(id)));
        return Collections.unmodifiableMap(result);
    }
}
