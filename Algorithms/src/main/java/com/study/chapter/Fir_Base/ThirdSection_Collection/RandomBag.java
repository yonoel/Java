package com.study.chapter.Fir_Base.ThirdSection_Collection;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

// 1.3.34
public class RandomBag<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new RandomBagIterator();
    }

    private class RandomBagIterator implements Iterator<T> {
        private int current;

        public RandomBagIterator() {
            for (int i = 0; i < n; i++) {
                int r = i + StdRandom.uniform(n - 1);
                T temp = (T) t[i];
                t[i] = t[r];
                t[r] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return current != n;
        }

        @Override
        public T next() {
            return t[current++];
        }
    }

    private T[] t;
    private int n;

    public RandomBag() {
    }

    public RandomBag(int cap) {
        t = (T[]) new Object[cap];
    }

    boolean isEmpty() {
        return n == 0;
    }

    boolean isFull() {
        return n == t.length;
    }

    void resize(int k) {
        T[] temp = (T[]) new Object[k];
        for (int i = 0; i < k; ++i) {
            temp[i] = t[i];
        }
        t = temp;
    }

    void add(T t) {
        if (isFull()) resize(2 * n);
        this.t[n++] = t;
    }
}
