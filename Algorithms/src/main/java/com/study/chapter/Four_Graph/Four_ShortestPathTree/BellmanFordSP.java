package com.study.chapter.Four_Graph.Four_ShortestPathTree;

import edu.princeton.cs.algs4.Queue;

public class BellmanFordSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQ;
    private Queue<Integer> queue;
    private int cost; // relax调用次数
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph g ,int s) {
        distTo = new double[g.getV()];
        edgeTo = new DirectedEdge[g.getV()];
        onQ = new boolean[g.getV()];
        queue = new Queue<>();
        for (int i = 0; i < g.getV(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = .0;
        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !this.hasNegativeCycle()){
            int v = queue.dequeue();
            onQ[v] = false;
            relax(g,v);
        }
    }
    private void relax(EdgeWeightedDigraph g,int v){
        for (DirectedEdge edge : g.adj(v)) {
            int w = edge.to();
            if(distTo[w] > distTo[v] + edge.getWeight()){
                distTo[w] = distTo[v] + edge.getWeight();
                edgeTo[w] = edge;
                if(!onQ[w]){
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            if( cost ++ % g.getV() == 0){
                findNegativeCycle();
            }
        }
    }
    private void  findNegativeCycle(){
        int v = edgeTo.length;
        EdgeWeightedDigraph gCopy = new EdgeWeightedDigraph(v);
        for (int i = 0; i < v; i++) {
            if(edgeTo[i] != null){
                gCopy.addEdge(edgeTo[i]);
            }
        }
        EdgeWeightedCycleFinder finder = new EdgeWeightedCycleFinder(gCopy);
        cycle = finder.cycle();
    }
    public boolean hasNegativeCycle(){
        return cycle != null;
    }
    public Iterable<DirectedEdge> negativeCycle(){
        return cycle;
    }

    public static void main(String[] args) {
        int s = 0;
        BellmanFordSP bellmanFordSP = new BellmanFordSP(EdgeWeightedDigraph.tinyEWDn(),s);
//        BellmanFordSP bellmanFordSP = new BellmanFordSP(EdgeWeightedDigraph.tinyEWDnc(),s);
    }
}
