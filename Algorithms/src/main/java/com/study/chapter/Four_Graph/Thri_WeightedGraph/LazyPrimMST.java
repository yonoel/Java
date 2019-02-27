package com.study.chapter.Four_Graph.Thri_WeightedGraph;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        this.marked = new boolean[G.getV()];
        mst = new Queue<>();
        pq = new MinPQ<>();
        visit(G,0);
        while (!pq.isEmpty()){
            Edge e = pq.delMin();
            int v = e.either(),w = e.other(v);
            if(marked[v] && marked[w]) continue;
            mst.enqueue(e);
            if(!marked[v]) visit(G,v);
            if(!marked[w]) visit(G,w);
        }
    }
    private void visit(EdgeWeightedGraph g,int v){
        marked[v] = true;
        for (Edge edge : g.adj(v)) {
            if(!marked[edge.other(v)]) pq.insert(edge);
        }
    }
    public double weight(){
        double sum = .0;
        for (Edge edge : mst) {
            sum += edge.getWeight();
        }
        return sum;
    }
    public Iterable<Edge> edges(){
        return mst;
    }
    public static void main(String[] args) {
        EdgeWeightedGraph EWG = EdgeWeightedGraph.tinyEWG();
        LazyPrimMST mst = new LazyPrimMST(EWG);
        System.out.println(mst.weight());
        mst.edges().forEach(System.out::println);
    }
}
