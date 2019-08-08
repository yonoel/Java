package buildBasicModule;

public interface Computable<A, V> {
    V compute(A args) throws InterruptedException;
}
