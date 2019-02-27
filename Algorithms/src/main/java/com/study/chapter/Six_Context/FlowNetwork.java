package com.study.chapter.Six_Context;

import com.study.chapter.Four_Graph.Thri_WeightedGraph.Edge;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;

public class FlowNetwork {
    private final int V;
    private int E;
    private Bag<FlowEdge>[] adsj;

    public FlowNetwork(int v, int e) {
        V = v;
        E = e;
        adsj = (Bag<FlowEdge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adsj[i] = new Bag<>();
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(FlowEdge edge) {
    }

    public Iterable<FlowEdge> adj(int v) {
        Stack<FlowEdge> stack = new Stack<>();
        for (FlowEdge flowEdge : adsj[v]) {
            stack.push(flowEdge);
        }
        return stack;
    }
//    public Iterable<FlowEdge> edges(){}
}
