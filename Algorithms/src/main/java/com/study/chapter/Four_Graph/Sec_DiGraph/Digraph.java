package com.study.chapter.Four_Graph.Sec_DiGraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int v) {
        V = v;
        E = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }
    public void addEdge(int v,int w){
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int j) {
        return adj[j];
    }
    public Digraph reverse(){
        Digraph digraph = new Digraph(V);
        for (int i = 0; i < V; i++) {
            for (Integer integer : adj(i)) {
                digraph.addEdge(integer,i);
            }
        }
        return digraph;
    }
    public static Digraph getInstance(){
        Digraph digraph = new Digraph(13);
        digraph.addEdge(4,2);
        digraph.addEdge(2,3);
        digraph.addEdge(3,2);
        digraph.addEdge(6,0);
        digraph.addEdge(0,1);
        digraph.addEdge(2,0);
        digraph.addEdge(11,12);
        digraph.addEdge(12,9);
        digraph.addEdge(9,10);
        digraph.addEdge(9,11);
        digraph.addEdge(8,9);
        digraph.addEdge(10,12);
        digraph.addEdge(11,4);
        digraph.addEdge(4,3);
        digraph.addEdge(3,5);
        digraph.addEdge(8,7);
        digraph.addEdge(7,8);
        digraph.addEdge(5,4);
        digraph.addEdge(0,5);
        digraph.addEdge(6,4);
        digraph.addEdge(6,9);
        digraph.addEdge(7,6);
        return digraph;
    }
    public static Digraph getDAG(){
        Digraph digraph = new Digraph(13);
        digraph.addEdge(8,7);

        digraph.addEdge(7,6);

        digraph.addEdge(2,3);
        digraph.addEdge(2,0);

        digraph.addEdge(3,5);

        digraph.addEdge(0,6);
        digraph.addEdge(0,1);
        digraph.addEdge(0,5);

        digraph.addEdge(6,9);
        digraph.addEdge(6,4);

        digraph.addEdge(9,10);
        digraph.addEdge(9,11);
        digraph.addEdge(9,12);

        digraph.addEdge(11,12);

        digraph.addEdge(5,4);

        return digraph;
    }

    public static void main(String[] args) {
        Digraph digraph = Digraph.getInstance();
    }
}
