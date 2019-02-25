package com.study.chapter.Four_Graph.Four_ShortestPathTree;

import com.study.chapter.Four_Graph.Sec_DiGraph.Toplogical;

// 这个有问题，出在排序上，
public class CPM {
    public static EdgeWeightedDigraph getJobsPC() {
        int N = 10;
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(2 * N + 2);
        int s = 2 * N, t = 2 * N + 1;
        String[][] test = {{"41.0", "1", "7", "9"}, {"51.0", "2"}, {"50.0"}, {"36.0"}, {"38.0"}, {"45.0"}, {"21.0", "3", "8"}, {"32.0", "3", "8"}
                , {"32.0", "2"}, {"29.0", "4", "6"}
        };
        for (int i = 0; i < N; i++) {
            String[] a = test[i];
            double duration = Double.parseDouble(a[0]);
            g.addEdge(new DirectedEdge(i, i + N, duration));
            g.addEdge(new DirectedEdge(s, i, .0));
            g.addEdge(new DirectedEdge(i + N, t, .0));
            for (int j = 1; j < a.length; j++) {
                int successor = Integer.parseInt(a[j]);
                g.addEdge(new DirectedEdge(i + N, successor, .0));
            }
        }

        AcyclicLP lp = new AcyclicLP(g,s,true);
        for (int i = 0; i < N; i++) {
            System.out.printf(" %4d: %5.1f \n",i,lp.distTo(i));
        }
        System.out.println("finsh time: " + lp.distTo(t));
        return g;
    }

    public static void main(String[] args) {
        getJobsPC();
//        int s = 20;
//        EdgeWeightedDigraph e = getJobsPC();
//        Toplogical top = new Toplogical(e);
//        AcyclicSP ap = new AcyclicSP();
//        AcyclicLP ap = new AcyclicLP(getJobsPC(),s,true);
//        for (int i = 0; i < 10; i++) {
//            System.out.printf(" %4d: %5.1f \n",i,ap.distTo(i));
//        }
//        System.out.println("finsh time: " + ap.distTo(21));
    }
}
