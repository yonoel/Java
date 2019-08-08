package assemblyObject;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class MutablePoint {
    public int x;
    public int y;

    public MutablePoint(MutablePoint mutablePoint) {

    }
}
