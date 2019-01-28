package com.study.chapter.Fir_Base;

import edu.princeton.cs.algs4.*;

public class DoublingRatio {
    public static void main(String[] args) {

        //      1、画出一条直线：A(0.3,0.2)、B(0,0)，
        // 两个坐标连接成的直线，因为默认是单位画布，所以坐标如果超过1的话是显示不出来的
       // StdDraw.line(0.3, 0.2, 0, 0);
        //      2、画出一个同心圆：圆心是(0.5,0.5)、半径是0.2
       // StdDraw.circle(0.5, 0.5, 0.2);
//      3、画出一个正方形中心点是(0.5,0.5),中心点做垂线到边的距离是0.2
//        StdDraw.square(0.5, 0.5, 0.2);
        /*
         * 5、画出一个多边形
         * 各个点分别是(0.2,0.1),(0.3,0.6),(0.4,0.9)
         * 各点之间连线，可得到一个多边形
         * */
       //  double[] x = {0.1, 0.1, 0.7,0.7};
       //  double[] y = {0.1, 0.6, 0.6,0.1};
       // StdDraw.polygon(x, y);

             // 7、画出单一的一个坐标点
       // StdDraw.point(0.3,0.4);
        int MAX = 2000;
        int i = 0;
        int step = 1;
        int len = MAX / step + 1;
        int[] arrN = new int[len];
        double[] timeN = new double[len];
        for (int N = 0; N <= MAX; N = N + step) {
            double time = timeTrial(N);
            // StdOut.printf("%7d %5.7f\n", N, time);
            arrN[i] = N;
            timeN[i++] = time;
        }
        drawStandard(arrN, timeN);
        // 第一个点也就是首个点
      /*  double [] prevPoint = {0,0};
        double [] nextPoint = {0,0};
        double prev ;
        // StdDraw.line(prevPoint[0],prevPoint[1],125/1000.0,prev);
        for (int N = 250; true; N+=N) {
            double time = timeTrial(N);
            nextPoint[0] = N;
            nextPoint[1] = time;
            int NLength = (N+"").length();
            int timelength = (time+"").length();
            StdDraw.line(prevPoint[0],prevPoint[1],N/Math.pow(10,NLength+1),time/Math.pow(10,timelength+1));
            prevPoint = nextPoint.clone();
            // StdOut.printf("%6d %7.1f",N,time);
            // N -> x time->y
        }*/
    }
    // 绘制标准图形
    public static void drawStandard(int[] n, double[] time) {
        StdDraw.setXscale(-0.5, 1.2 * n[n.length - 1] / 1000.0);
        StdDraw.setYscale(-0.5, time[n.length - 1] * 1.2);
        // 建立坐标系
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.001);
        StdDraw.line(0, 0, 1.1 * n[n.length - 1] / 1000, 0);
        StdDraw.line(0, 0, 0, 1.1 * time[n.length - 1]);
        for (int i = 1; i < 1.1 * n[n.length - 1] / 1000.0; i++)
            StdDraw.text(i, -0.2, i + "K");
        for (double t = 2; t < time[n.length - 1] * 1.1; t += 2)
            StdDraw.text(-0.2, t, t + "");
        // 绘制图像
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.setPenRadius(0.02);
        for (int i = 0; i < n.length; i++)
            StdDraw.point(n[i] / 1000.0, time[i]);
    }


    public static double timeTrial(int n) {
        //    为处理N个随机的6位整数的ThreeSum.count()计时
        int MAX = 1000000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        int cnt = ThreeSum.count(a);
        return timer.elapsedTime();

    }
}
