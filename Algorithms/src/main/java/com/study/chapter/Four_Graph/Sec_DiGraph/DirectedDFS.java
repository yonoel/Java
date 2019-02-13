package com.study.chapter.Four_Graph.Sec_DiGraph;

import edu.princeton.cs.algs4.Bag;

public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph g, int s) {
        marked = new boolean[g.getV()];
        dfs(g, s);
    }

    public DirectedDFS(Digraph g, Iterable<Integer> sources) {
        marked = new boolean[g.getV()];
        for (int s : sources) {
            if (!marked[s]) dfs(g, s);
        }
    }

    private void dfs(Digraph g, int s) {
        marked[s] = true;
        for (int w : g.adj(s)) {
            if (!marked[w]) dfs(g, w);
        }
    }

    public boolean marked(int n) {
        return marked[n];
    }

    public static void main(String[] args) {
        Digraph g = Digraph.getInstance();
        Bag<Integer> sources = new Bag<>();
        sources.add(1);
        DirectedDFS directedDFS = new DirectedDFS(g, 6);

        for (int i = 0; i < g.getV(); i++) {
            if (directedDFS.marked(i)) System.out.println(i + "\n");

        }
    }
}
