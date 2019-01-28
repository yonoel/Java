package com.study.chapter.Fir_Base.ThirdSection_Collection;

public class FixedCapacityStack<T> {
    private T[] a;
    private int n;

    public FixedCapacityStack(int n, T t) {
        a = (T[]) new Object[n];
    }

    boolean isEmpty() {
        return n == 0;
    }

    int size() {
        return n;
    }

    void push(T s) {
        a[n++] = s;
    }

    T pop() {
        return a[--n];
    }
}
