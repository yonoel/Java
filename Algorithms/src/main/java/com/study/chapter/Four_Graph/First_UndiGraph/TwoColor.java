package com.study.chapter.Four_Graph.First_UndiGraph;

public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph graph) {
        marked = new boolean[graph.V()];
        color = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i])
                dfs(graph, i);
        }
    }

    private void dfs(Graph graph, int i) {
        marked[i] = true;
        for (int w : graph.adj(i)) {
            if (!marked[w]) {
                color[w] = !color[i];
                dfs(graph, w);
            } else if (color[w] == color[i]) isTwoColorable = false;
        }
    }
}
