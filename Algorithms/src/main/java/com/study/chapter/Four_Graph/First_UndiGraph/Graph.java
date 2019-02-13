package com.study.chapter.Four_Graph.First_UndiGraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.Iterator;

public class Graph {
    private final int V;// 顶点
    private int E;    //边
    private Bag<Integer>[] adjs;// 邻接表

    public Graph(int v) {
        V = v;
        adjs = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adjs[i] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v,w);
        }
    }

    int V() {
        return V;
    }

    int E() {
        return E;
    }

    void addEdge(int v, int w) {
        adjs[v].add(w);
        adjs[w].add(v);
        E++;
    }

    Iterable<Integer> adj(int x) {
        return adjs[x];
    }

    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) degree++;
        return degree;
    }

    public static int maxDegree(Graph G) {
        int max = 0;
        for (int i = 0; i < G.V(); i++) {
            if (degree(G, i) > max) max = degree(G, i);
        }
        return max;
    }

    public static double avgDegree(Graph G) {
        return 2 * G.E() / G.V();
    }

    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int i = 0; i < G.V(); i++) {
            for (int w : G.adj(i))
                if (i == w) count++;
        }
        return count;
    }

    @Override
    public String toString() {
       String s = V + " vertices, " + E + " edges \n,";
        for (int i = 0; i < V; i++) {
            s+= i+": ";
            for (int w : this.adj(i))
                s+= w + " ";
            s+= "\n";
        }
        return s;
    }
    public static Graph getInstance(){
        Graph graph = new Graph(6);
        graph.addEdge(0, 5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(2,4);
        graph.addEdge(5,3);
        graph.addEdge(4,3);
        return graph;
    }
}
