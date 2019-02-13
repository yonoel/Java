package com.study.chapter.Four_Graph.First_UndiGraph;
// 这种就是先走到无路可走在退回来
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v))
            if (!marked[w]) dfs(G, w);
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return --count;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(0, 5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
//        graph.addEdge(2,4);
//        graph.addEdge(5,3);
//        graph.addEdge(4,3);

        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph, 0);
        System.out.println(depthFirstSearch.count());
    }
}
