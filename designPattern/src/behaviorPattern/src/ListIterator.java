package behaviorPattern.src;

public class ListIterator<T> extends Iterator<T> {
    public ListIterator(List<T> list) {
        this.list = list;
        this.current = 0;
    }

    private List<T> list;
    private int current;

    @Override
    void first() {
        this.current = 0;
    }

    @Override
    void next() {
        this.current++;
    }

    @Override
    boolean isDone() {
        return current == list.getSize();
    }

    @Override
    T currentItem() {
        if (isDone()) throw new ArrayIndexOutOfBoundsException();
        return this.list.get(current);
    }
}
