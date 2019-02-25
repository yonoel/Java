package com.study.chapter.Four_Graph.Four_ShortestPathTree;

import com.study.chapter.Four_Graph.Sec_DiGraph.Digraph;
import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedCycleFinder {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;
    private Stack<DirectedEdge> edgeStack;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph g) {
        onStack = new boolean[g.getV()];
        marked = new boolean[g.getV()];
        edgeTo = new DirectedEdge[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            if (!marked[i]) dfs(g, i);
        }
    }

    private void dfs(EdgeWeightedDigraph g, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (DirectedEdge edge : g.adj(v)) {
            if (this.hasCycle()) return;
            int w = edge.to();
            edgeTo[w] = edge;
            if (!marked[w]) {
                dfs(g, w);
            } else if (onStack[w]) {
//                cycle = new Stack<>();
                edgeStack = new Stack<>();
                // 不能用边遍历，因为边是个环啊！
//                for (DirectedEdge i = edge; i != null; i = edgeTo[edge.from()]) {
//                    cycle.push();
//                    edgeStack.push(edge);
//                }
//                edgeTo[w] = edge;//???
                edgeStack.push(edge); // edge v->w
                for (int i = v; i != w; i = edgeTo[v].from()) {
//                    cycle.push(i);
                    edgeStack.push(edgeTo[i]);
                }
//                cycle.push(w);
//                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    private boolean hasCycle() {
        return edgeStack != null;
//        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        if (!hasCycle()) return null;
        return edgeStack;
//        return cycle;
    }
}
