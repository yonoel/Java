package com.study.chapter.Four_Graph.Four_ShortestPathTree;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph g, int s) {
        this.edgeTo = new DirectedEdge[g.getV()];
        distTo = new double[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = .0;
        pq = new IndexMinPQ<>(g.getV());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            relax(g, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge edge : g.adj(v)) {
            int w = edge.to();
            if (distTo[w] > distTo[v] + edge.getWeight()) {
                distTo[w] = distTo[v] + edge.getWeight();
                edgeTo[w] = edge;
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph t = EdgeWeightedDigraph.tinyEWG();
        int s = 0;
        DijkstraSP sp = new DijkstraSP(t, s);
        for (int i = 0; i < t.getV(); i++) {
            System.out.println(s + " to " + i + ": " + sp.distTo(i));
            if (sp.hasPathTo(i)) {
                for (DirectedEdge edge : sp.pathTo(i)) {
                    System.out.print(edge);
                }
                if (sp.distTo(i) != .0)
                    System.out.println();
            }
        }
    }
}
