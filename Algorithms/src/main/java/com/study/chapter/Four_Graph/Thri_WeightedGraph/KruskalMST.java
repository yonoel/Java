package com.study.chapter.Four_Graph.Thri_WeightedGraph;

import edu.princeton.cs.algs4.MinPQ;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph g) {
        mst = new Queue<>();
        Edge[] edges =  StreamSupport.stream(g.edges().spliterator(),false).toArray(Edge[]::new);
        MinPQ<Edge> pq = new MinPQ<>(edges);
        UF uf = new UF(g.getV());
        while (!pq.isEmpty() && mst.size() < g.getV() - 1){
            Edge e = pq.delMin();
            int v = e.either(),w = e.other(v);
            if (uf.connected(v,w)) continue;
            uf.union(v,w);
            mst.enqueue(e);
        }
    }
    public Iterable<Edge> edges(){
        return mst;
    }
    public double weight(){
        return StreamSupport.stream(edges().spliterator(),true).mapToDouble(Edge::getWeight).sum();
    }
    public static void main(String[] args) {
        KruskalMST mst = new KruskalMST(EdgeWeightedGraph.tinyEWG());
        System.out.println(mst.weight());
        mst.edges().forEach(System.out::println);
    }
}
