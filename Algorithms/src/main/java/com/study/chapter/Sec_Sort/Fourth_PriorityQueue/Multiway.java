package com.study.chapter.Sec_Sort.Fourth_PriorityQueue;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Multiway {
    // 把一个个堆归并成一个堆
    public static void merge(In[] streams){
        int N = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<>(N);
        for (int i = 0; i < N; i++) pq.insert(i,streams[i].readString());
        while (!pq.isEmpty()){
            StdOut.println(pq.minKey());
            int i = pq.delMin();
            if(!streams[i].isEmpty()) pq.insert(i,streams[i].readString());
        }
    }

    public static void main(String[] args) {
        In[] streams = new In[5];
        for (int i = 0; i < 5; i++) {
            streams[i] = new In(String.valueOf(i));
        }
        merge(streams);
    }
}
