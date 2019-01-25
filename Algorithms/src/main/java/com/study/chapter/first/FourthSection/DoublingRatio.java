package com.study.chapter.first.FourthSection;

import edu.princeton.cs.algs4.StdOut;

public class DoublingRatio {

    public static void main(String[] args) {
        double prev = DoublingTest.timeTrial(125);
        for (int i = 250; true; i += i) {
            double time = DoublingTest.timeTrial(i);
            StdOut.printf("%6d %7f ", i, time);
            StdOut.printf("%5.1f \n", time / prev);
            prev = time;
        }
    }

}
