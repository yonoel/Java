package com.study.chapter.Sec_Sort.Thrid_Quick;

import com.study.chapter.Sec_Sort.Example;
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

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            // 扫描左
            while (Example.less(a[++i], v)) if (i == hi) break;
            // 扫描右
            while (Example.less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            Example.exch(a, i, j);
        }
        Example.exch(a, lo, hi);
        return j;
    }
}
