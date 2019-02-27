package com.study.chapter.Sec_Sort.Fourth_PriorityQueue;

import edu.princeton.cs.algs4.StdRandom;

import static com.study.chapter.Sec_Sort.Thrid_Quick.Quick.partition;

public class Selector<Key extends Comparable> {
    public static Comparable select(Comparable[] a,int k){
        StdRandom.shuffle(a);
        int lo = 0;
        int hi = a.length - 1;
        while (hi > lo){
            int j = (int) partition(a,lo,hi);
            if (j == k) return a[k];
            else if(j > k) hi = j - 1;
            else if(j < k) lo = j + 1;
        }
        return a[k];
    }
}
