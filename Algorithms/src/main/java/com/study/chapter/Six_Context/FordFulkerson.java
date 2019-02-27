package com.study.chapter.Six_Context;


import edu.princeton.cs.algs4.Queue;

public class FordFulkerson {
    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double value;

    public FordFulkerson(FlowNetwork g, int s, int t) {
        while (hasArgumentingPath(g, s, t)) {
            double bottle = Double.POSITIVE_INFINITY;
            for (int i = t; i != s; i = edgeTo[i].other(i)) {
                bottle = Math.min(bottle, edgeTo[i].residualCapacityTo(i));
            }
            for (int i = t; i != s; i = edgeTo[i].other(i)) {
                edgeTo[i].addResidualFlowTo(i, bottle);
            }
            value += bottle;
        }
    }

    private boolean hasArgumentingPath(FlowNetwork g, int s, int t) {
        marked = new boolean[g.getV()];
        edgeTo = new FlowEdge[g.getV()];
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (FlowEdge edge : g.adj(v)) {
                int w = edge.other(v);
                if (edge.residualCapacityTo(w) > 0 && !marked[w]) {
                    edgeTo[w] = edge;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
        return marked[t];
    }
}
