package com.study.sort.classes;

import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (Example.less(a[++i], v)) if (i == hi) break;
            while (Example.less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            Example.exch(a, i, j);
        }
        Example.exch(a, lo, hi);
        return j;
    }
}
