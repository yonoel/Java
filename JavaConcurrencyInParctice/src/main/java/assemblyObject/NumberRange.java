package assemblyObject;

import net.jcip.annotations.NotThreadSafe;

import java.util.concurrent.atomic.AtomicLong;
@NotThreadSafe
public class NumberRange {
    // lower <= upper
    private final AtomicLong lower = new AtomicLong(0);
    private final AtomicLong upper = new AtomicLong(0);

    public void setLower(int i) {
        if (i > upper.get()) {
            throw new IllegalArgumentException("lower must low than upper");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        if (i < lower.get())
            throw new IllegalArgumentException("upper must big than lower");
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get() && i <= upper.get());
    }
}
