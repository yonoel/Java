package com.study.chapter.Four_Graph.Thri_WeightedGraph;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph g) {
        this.edgeTo = new Edge[g.getV()];
        distTo = new double[g.getV()];
        marked = new boolean[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        pq = new IndexMinPQ<>(g.getV());
        distTo[0] = .0;
        pq.insert(0,.0);
        while (!pq.isEmpty())
            visit(g,pq.delMin());
    }
    private void visit(EdgeWeightedGraph g,int v){
        marked[v] = true;
        for (Edge edge : g.adj(v)) {
            int w = edge.other(v);
            // 这样只是对当前节点的边，并不是最小树
//            if(edgeTo[w] != null) continue;
            if(marked[w]) continue;
            if(edge.getWeight() < distTo[w]){
                edgeTo[w] = edge;
                distTo[w] = edge.getWeight();
                if(pq.contains(w)) pq.changeKey(w,distTo[w]);
                else pq.insert(w,distTo[w]);
            }
        }
    }
    public double weight(){
        return Arrays.stream(distTo).sum();
    }
    public Iterable<Edge> edges(){
        return Arrays.stream(edgeTo).collect(Collectors.toList());
    }
    public static void main(String[] args) {
        EdgeWeightedGraph e = EdgeWeightedGraph.tinyEWG();
        PrimMST st = new PrimMST(e);
        System.out.println(st.weight());
        st.edges().forEach(o->{
            System.out.println(o);
        });
    }
}
