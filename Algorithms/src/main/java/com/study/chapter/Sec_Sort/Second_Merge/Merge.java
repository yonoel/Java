package com.study.chapter.Sec_Sort.Second_Merge;

import com.study.chapter.Sec_Sort.Example;

public class Merge<T extends Comparable>{
    private static Comparable[] aux; //  用来存放比对结果
    /**
     * 原地归并的方法
     */
    public static void merge (Comparable[] a, int lo, int mid, int hi) {
        //    将a[lo...mid] 和 a[mid...hi] 合并
        int i = lo, j = mid + 1;
        aux = new Comparable[a.length];
        for (int k = lo; k <= hi; k++) {
            //    将a[lo...hi]复制到 aux[lo...hi]
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (Example.less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    // 自上而下，树状图，分左右
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);// 递归调用，单独排序左边
        sort(a, mid, hi);// 排序右边
        merge(a, lo, mid, hi);
    }

}
