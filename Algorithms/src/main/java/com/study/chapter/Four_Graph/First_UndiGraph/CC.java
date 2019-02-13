package com.study.chapter.Four_Graph.First_UndiGraph;

public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph graph) {
        marked = new boolean[graph.V()];
        id = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
                count++;
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : graph.adj(v)) {
            if (!marked[w])
                dfs(graph, w);
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Graph graph = Graph.getInstance();
        CC cc = new CC(graph);
        System.out.println(cc.id(2));
    }
}
