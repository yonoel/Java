package sharedObject;

public class AssertHolder {
    public int value;

    public AssertHolder(int value) {
        this.value = value;
    }

    public void assertSanity() {
        if (value != value)
            throw new AssertionError("this statement is false");
    }
}
