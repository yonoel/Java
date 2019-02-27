package com.study.chapter.Four_Graph.Four_ShortestPathTree;

import edu.princeton.cs.algs4.Stack;

public class CommonSP {
    private double[] distTo; // 顶点索引数组，
    private DirectedEdge[] edgeTo;

    //    private Stack<Integer> integers;
    public CommonSP(EdgeWeightedDigraph g, int start) {
        distTo = new double[g.getV()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[start] = .0;
        edgeTo = new DirectedEdge[g.getV()];
//        integers = new Stack<>();
        relax(g, start);

//        while (!integers.isEmpty()){
//            relax(g,integers.pop());
//        }
    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge directedEdge : g.adj(v)) {
            int w = directedEdge.to();
            if (distTo[w] > distTo[v] + directedEdge.getWeight()) {
                distTo[w] = distTo[v] + directedEdge.getWeight();
                edgeTo[w] = directedEdge;
                relax(g, w);
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
        EdgeWeightedDigraph e = EdgeWeightedDigraph.tinyEWG();
        int start = 2;
        CommonSP sp = new CommonSP(e, start);

        for (int i = 0; i < e.getV(); i++) {
            System.out.println(start + " to " + i + ": " + sp.distTo(i));
            if (sp.hasPathTo(i)) {
                for (DirectedEdge edge : sp.pathTo(i)) {
                    System.out.print(edge);
                }
                if (sp.distTo(i) != .0)
                    System.out.println();
            }
        }
//        System.out.println(sp.hasPathTo(2));
//        System.out.println(sp.pathTo(2));
    }
}
