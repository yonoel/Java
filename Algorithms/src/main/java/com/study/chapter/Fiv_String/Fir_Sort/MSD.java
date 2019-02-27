package com.study.chapter.Fiv_String.Fir_Sort;


import com.study.chapter.Sec_Sort.Example;
import com.study.chapter.Sec_Sort.First_Primary.Insertion;

public class MSD {
    private static int R = 256;
    private static int M = 15;
    private static String[] aux;

    public static int charAt(String s, int d) {
        // 因为字符串可能长度不统一，当前不存在这个字符怎么办
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    private static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        // 将d个字符为键 ; 快排
        if (hi <= lo + M) {
            insertion(a,lo,hi,d);
            return;
        }
//        if (hi <= lo + M) return;
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            int n = charAt(a[i], d);
            // 如果不存在返回 -1 那么+1就是0了，但是这种排序本来就需要一个额外的空的位置，于是 统一+2
            count[n + 2]++;
        }
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    public static String[] getAux() {
        return new String[]{
                "she"
                , "sells"
                , "seashells"
                , "by"
                , "the"
                , "sea"
                , "shore"
                , "the"
                , "shells"
                , "she"
                , "shells"
                , "are"
                , "surely"
                , "seashells"
                , "seashel"
                , "seashell"
                , "seashellsab"
                , "seashellses"
                , "seashe"
                , "seashily"

        };
    }
    // insertion sort a[lo..hi], starting at dth character
    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }
    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at character d
    private static boolean less(String v, String w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }
    public static void main(String[] args) {
        String[] a = getAux();
        System.out.println("before --------");
        for (int i = a.length - 1; i >= 0; i--) {
            System.out.println(a[i]);
        }
        MSD.sort(a);
        System.out.println("after -------");
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
