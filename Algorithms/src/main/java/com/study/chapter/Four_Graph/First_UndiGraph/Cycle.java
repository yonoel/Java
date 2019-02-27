package com.study.chapter.Four_Graph.First_UndiGraph;

public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph graph) {
        marked = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i])
                dfs(graph, i, i);
        }
    }

    private void dfs(Graph graph, int i, int u) {
        marked[i] = true;
        for (int w : graph.adj(i)) {
            if (!marked[w]) {
                dfs(graph, w, i);
            } else if (w != u) hasCycle = true;
        }
    }
    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = Graph.getInstance();
        Cycle cycle = new Cycle(graph);
        System.out.println(cycle.hasCycle);
    }
}
