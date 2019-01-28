package com.study.chapter.Sec_Sort.classes;

public class Insertion {
    public static void sort(Comparable [] a){
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j >0 && Example.less(a[j],a[j-1]); j--) {
                Example.exch(a,j,j-1);
            }
        }
    }
}
