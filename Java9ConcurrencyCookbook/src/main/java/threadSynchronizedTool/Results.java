package threadSynchronizedTool;

public class Results {
    private final int data[];

    public Results(int size) {
        this.data = new int[size];
    }

    public void setData(int index, int value) {
        data[index] = value;
    }

    public int[] getData() {
        return data;
    }
}
