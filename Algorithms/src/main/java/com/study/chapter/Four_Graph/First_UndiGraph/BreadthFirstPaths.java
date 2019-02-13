package com.study.chapter.Four_Graph.First_UndiGraph;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

// 广度就是到一个路口，大家分开，走不同都路，然后如此往复
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(int s, Graph graph) {
        this.s = s;
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        bfs(graph, s);
    }

    private void bfs(Graph g, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int n) {
        return marked[n];
    }

    public Iterable<Integer> pathTo(int n) {
        if (!hasPathTo(n)) return null;
        Queue<Integer> paths = new Queue<>();
        // n 是起点
        int start = n;
        int end = s;
        while (start != end) {
            paths.enqueue(start);
            start = edgeTo[start];
        }
        paths.enqueue(end);
        return paths;
    }

    public static void main(String[] args) {
        Graph graph = Graph.getInstance();
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(3, graph);
        System.out.println(" 3 to 0's path is:");
        for (Integer integer : breadthFirstPaths.pathTo(0)) {
            System.out.println(integer + "\n");
        }
//        for (int i = 0; i < breadthFirstPaths.edgeTo.length; i++) {
//            System.out.println(i+" to " + breadthFirstPaths.edgeTo[i]+"\n");
//        }
    }
}
