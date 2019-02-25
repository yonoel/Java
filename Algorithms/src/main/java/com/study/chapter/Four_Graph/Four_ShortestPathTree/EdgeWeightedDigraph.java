package com.study.chapter.Four_Graph.Four_ShortestPathTree;

import edu.princeton.cs.algs4.Bag;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adjs;

    public EdgeWeightedDigraph(int v) {
        V = v;
        this.E = 0;
        adjs = (Bag<DirectedEdge>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adjs[i] = new Bag<DirectedEdge>();
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(DirectedEdge e) {
        adjs[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adjs[v];
    }
    public Iterable<DirectedEdge> edges(){
        Bag<DirectedEdge> bag = new Bag<>();
        for (int i = 0; i < getV(); i++) {
            for (DirectedEdge directedEdge : adj(i)) {
                bag.add(directedEdge);
            }
        }
        return bag;
    }
    public static EdgeWeightedDigraph tinyEWDn(){
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(8);
        g.addEdge(new DirectedEdge(4,5,.35));
        g.addEdge(new DirectedEdge(5,4,.35));
        g.addEdge(new DirectedEdge(4,7,.37));
        g.addEdge(new DirectedEdge(5,7,.28));
        g.addEdge(new DirectedEdge(7,5,.28));
        g.addEdge(new DirectedEdge(5,1,.32));
        g.addEdge(new DirectedEdge(0,4,.38));
        g.addEdge(new DirectedEdge(0,2,.26));
        g.addEdge(new DirectedEdge(7,3,.39));
        g.addEdge(new DirectedEdge(1,3,.29));
        g.addEdge(new DirectedEdge(2,7,.34));

        g.addEdge(new DirectedEdge(6,2,-1.20));
        g.addEdge(new DirectedEdge(3,6,.52));
        g.addEdge(new DirectedEdge(6,0,-1.4));
        g.addEdge(new DirectedEdge(6,4,-1.25));

        return g;
    }
    public static EdgeWeightedDigraph tinyEWDnc(){
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(8);
        g.addEdge(new DirectedEdge(4,5,.35));
        g.addEdge(new DirectedEdge(5,4,-0.66));
        g.addEdge(new DirectedEdge(4,7,0.37));
        g.addEdge(new DirectedEdge(5,7,.28));
        g.addEdge(new DirectedEdge(7,5,.28));
        g.addEdge(new DirectedEdge(5,1,.32));
        g.addEdge(new DirectedEdge(0,4,.38));
        g.addEdge(new DirectedEdge(0,2,.26));
        g.addEdge(new DirectedEdge(7,3,.39));
        g.addEdge(new DirectedEdge(1,3,.29));
        g.addEdge(new DirectedEdge(2,7,.34));
        g.addEdge(new DirectedEdge(6,2,.40));
        g.addEdge(new DirectedEdge(3,6,.52));
        g.addEdge(new DirectedEdge(6,0,.58));
        g.addEdge(new DirectedEdge(6,4,.93));

        return g;
    }
    public static EdgeWeightedDigraph tinyEWDAG(){
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(8);
        g.addEdge(new DirectedEdge(5,4,.35));
        g.addEdge(new DirectedEdge(4,7,.37));
        g.addEdge(new DirectedEdge(5,7,.28));
        g.addEdge(new DirectedEdge(5,1,.32));
        g.addEdge(new DirectedEdge(4,0,.38));
        g.addEdge(new DirectedEdge(0,2,.26));
        g.addEdge(new DirectedEdge(3,7,.39));
        g.addEdge(new DirectedEdge(1,3,.29));
        g.addEdge(new DirectedEdge(7,2,.34));
        g.addEdge(new DirectedEdge(6,2,.40));
        g.addEdge(new DirectedEdge(3,6,.52));
        g.addEdge(new DirectedEdge(6,0,.58));
        g.addEdge(new DirectedEdge(6,4,.93));
        return g;
    }
    public static EdgeWeightedDigraph tinyEWG(){
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(8);
        g.addEdge(new DirectedEdge(4,5,.35));
        g.addEdge(new DirectedEdge(5,4,.35));
        g.addEdge(new DirectedEdge(4,7,.37));
        g.addEdge(new DirectedEdge(5,7,.28));
        g.addEdge(new DirectedEdge(7,5,.28));
        g.addEdge(new DirectedEdge(5,1,.32));
        g.addEdge(new DirectedEdge(0,4,.38));
        g.addEdge(new DirectedEdge(0,2,.26));
        g.addEdge(new DirectedEdge(7,3,.39));
        g.addEdge(new DirectedEdge(1,3,.29));
        g.addEdge(new DirectedEdge(2,7,.34));
        g.addEdge(new DirectedEdge(6,2,.40));
        g.addEdge(new DirectedEdge(3,6,.52));
        g.addEdge(new DirectedEdge(6,0,.58));
        g.addEdge(new DirectedEdge(6,4,.93));
        return g;
    }
}
