package com.study.chapter.Sec_Sort.classes;

public class Quick3way {
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) Example.exch(a, lt++, i++);
            else if (cmp > 0) Example.exch(a, i, gt--);
            else i++;
        }
        sort(a,lo,lt-i);
        sort(a,gt+1,hi);
    }
}
