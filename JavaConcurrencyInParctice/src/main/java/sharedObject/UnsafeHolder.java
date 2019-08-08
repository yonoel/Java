package sharedObject;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class UnsafeHolder {
    private int value;
    public UnsafeHolder holder;

    private UnsafeHolder(int value) {
        this.value = value;
    }

    public void initialize(){
        holder = new UnsafeHolder(42);
    }
}
