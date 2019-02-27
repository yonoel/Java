package com.study.chapter.Four_Graph.Thri_WeightedGraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private Bag<Edge>[] adjs;

    public EdgeWeightedGraph(int v) {
        V = v;
        this.E = 0;
        adjs = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adjs[i] = new Bag<Edge>();
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }
    public void addEdge(Edge e){
        int v = e.either(),w = e.other(v);
        adjs[v].add(e);
        adjs[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adjs[v];
    }

    public Iterable<Edge> edges() {
        Stack<Edge> edgeStack = new Stack<>();
        for (int i = 0; i < V; i++) {
            for (Edge edge : adj(i)) {
                edgeStack.push(edge);
            }
        }
        return edgeStack;
    }
    public static EdgeWeightedGraph tinyEWG(){
        EdgeWeightedGraph g = new EdgeWeightedGraph(8);
        g.addEdge(new Edge(4,5,.35));
        g.addEdge(new Edge(4,7,.37));
        g.addEdge(new Edge(5,7,.28));
        g.addEdge(new Edge(0,7,.16));
        g.addEdge(new Edge(1,5,.32));
        g.addEdge(new Edge(0,4,.38));
        g.addEdge(new Edge(2,3,.17));
        g.addEdge(new Edge(1,7,.19));
        g.addEdge(new Edge(0,2,.26));
        g.addEdge(new Edge(1,2,.36));
        g.addEdge(new Edge(1,3,.29));
        g.addEdge(new Edge(2,7,.34));
        g.addEdge(new Edge(6,2,.40));
        g.addEdge(new Edge(3,6,.52));
        g.addEdge(new Edge(6,0,.58));
        g.addEdge(new Edge(6,4,.93));
        return g;
    }
}
