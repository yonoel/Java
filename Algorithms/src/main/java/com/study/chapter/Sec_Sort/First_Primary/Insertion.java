package com.study.chapter.Sec_Sort.First_Primary;

import com.study.chapter.Sec_Sort.Example;

public class Insertion {
    public static void sort(Comparable [] a){
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j >0 && Example.less(a[j],a[j-1]); j--) {
                Example.exch(a,j,j-1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {1,2,1,3,4,5,6};
        Insertion.sort(a);
        assert com.study.chapter.Sec_Sort.First_Primary.Example.isSorted(a);
    }
}
