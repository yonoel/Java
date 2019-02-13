package com.study.chapter.Four_Graph.First_UndiGraph;

import edu.princeton.cs.algs4.Stack;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(int s, Graph graph) {
        this.marked = new boolean[graph.V()];
        this.edgeTo = new int[graph.V()];
        this.s = s;
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    public boolean hasPathTo(int n) {
        return marked[n];
    }

    public Iterable<Integer> pathTo(int n) {
        if (!hasPathTo(n)) return null;
        Stack<Integer> paths = new Stack<>();
        for (int i = n; i != s; i = edgeTo[n]) {
            paths.push(i);
        }
        return paths;
    }

    public static void main(String[] args) {
        Graph graph = Graph.getInstance();
        Graph graph1 = new Graph(3);
        graph1.addEdge(0,1);
        graph1.addEdge(0,2);
        graph1.addEdge(1,2);

//        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(0,graph);
        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(0,graph1);
    }
}
