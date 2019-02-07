package com.study.chapter.Thr_Select.Fir_Base;

import com.study.chapter.Fir_Base.ThirdSection_Collection.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int n;

    public BinarySearchST(int cap) {
        keys = (Key[]) new Comparable[cap];
        values = (Value[]) new Object[cap];
    }

    int size() {
        return n;
    }

    boolean isEmpty() {
        return n == 0;
    }

    Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return values[i];
        return null;
    }


    void put(Key key, Value value) {
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) values[i] = value;
        else {
            // resize array
            for (int j = n; j > i; j--) {
                keys[j] = keys[j - 1];
                values[j] = values[j - 1];
            }
            keys[i] = key;
            values[i] = value;
            n++;
        }
    }

    Key min() {
        return keys[0];
    }

    Key max() {
        return keys[n - 1];
    }

    Key select(int k) {
        return keys[k];
    }

    Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    Key floor(Key key) {
        int i = rank(key);
        return keys[++i];
    }

    void delete(Key key) {
        int i = rank(key);
        // resize
    }

    Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }

    boolean contains(Key key) {
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) return true;
        return false;
    }

    int rank(Key key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) return rank(key, lo, mid - 1);
        else if (cmp > 0) return rank(key, mid + 1, hi);
        else return mid;
    }

    int rank(Key key) {
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }
}
