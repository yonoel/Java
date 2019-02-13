package com.study.chapter.Four_Graph.First_UndiGraph;

import edu.princeton.cs.algs4.StdIn;

public class DegreesOfSpearation {
    public static void main(String[] args) {
        SymbolGraph symbolGraph = new SymbolGraph(args[0],args[1]);
        Graph graph = symbolGraph.getGraph();
        String source = args[2];
        if(!symbolGraph.contains(source)){
            System.out.println("not is database.");
            return;
        }
        int s = symbolGraph.index(source);
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(s,graph);
        while (!StdIn.isEmpty()){
            String sink = StdIn.readLine();
            if(symbolGraph.contains(sink)){
                int t = symbolGraph.index(sink);
                if(breadthFirstPaths.hasPathTo(t)){
                    for (int v : breadthFirstPaths.pathTo(t)){
                        System.out.println("   "+symbolGraph.name(v));
                    }
                }else{
                    System.out.println(" not connected ");
                }
            }
        }
    }
}
