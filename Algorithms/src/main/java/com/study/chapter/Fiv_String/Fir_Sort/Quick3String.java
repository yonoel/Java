package com.study.chapter.Fiv_String.Fir_Sort;


public class Quick3String {
    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        return -1;
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, i, gt--);
            else i++;
        }
        // 三向中的前序在排序
        sort(a, lo, lt - 1, d);
        // 三向中的中序在排序，但是排除首字母
        if (v >= 0) sort(a, lt, gt, d + 1);
        // 三向中的后序在排序
        sort(a, gt + 1, hi, d);
    }

    private static void exch(String[] a, int i, int i1) {
        String temp = a[i];
        a[i] = a[i1];
        a[i1] = temp;
    }

    public static void main(String[] args) {
        String[] a = MSD.getAux();
        for (int i = a.length - 1; i >= 0; i--) {
            System.out.println(a[i]);
        }
        Quick3String.sort(a);
        System.out.println();
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
