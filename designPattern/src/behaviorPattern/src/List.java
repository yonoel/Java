package behaviorPattern.src;

public abstract class List<T> {
    private T[] ts;
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List(int size) {
        this.size = size;
        ts = (T[]) new Object[this.size];
    }

    T get(int index) {
        return ts[index];
    }
}
