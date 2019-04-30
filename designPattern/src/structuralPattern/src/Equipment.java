package structuralPattern.src;

import java.util.Iterator;

public abstract class Equipment implements Iterable{
    private String name;
    // 返回功耗
    abstract int power();
    abstract double netPrice();
    abstract double discountPrice();

    abstract void add(Equipment equipment);

    abstract void Remove(Equipment equipment);

    @Override
    public Iterator iterator() {
        return null;
    }
}
