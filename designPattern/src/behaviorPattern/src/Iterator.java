package behaviorPattern.src;

public abstract class Iterator<T> {
    abstract void first();
    abstract void next();
    abstract boolean isDone();
    abstract T currentItem();

}
