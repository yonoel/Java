package com.study.chapter.Sec_Sort.First_Primary;

import com.study.chapter.Sec_Sort.Example;

public class Selection {
    public static void sort(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for(int j= i+1;j<N;j++)
                if(Example.less(a[j],a[i])) min = j;
            Example.exch(a,i,min);
        }
    }
}
