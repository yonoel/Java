package com.study.chapter.Fir_Base;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Scanner;

public class FirstChapterQuestionAndAnswer {
    public static void main(String[] args) {
        int N = 5;
        double x = 10.0;
        double y = 10.0;
        double r = 9.0;
        drawCircle(x, y, r);
        double[][] points = drawPoints(x, y, r, N);
        // binomial(100, 50, 0,0);
        // soutGcd(64, 51);
        // getTable();
        // System.out.println(lg(5));
        // System.out.println(lg(9));
        // int rows = 3,com = 5;
        // String[][] a = generateMatrix(3,5);
        // soutMatrix(a);
        // String[][] b = transferMatrix(a);
        // soutMatrix(b);
        // test();
    }
    // 提高题要看高数
    //1.1.31
    // 随机连接。
    // 编写一段程序，从命令行接受一个整数N 和double 值p（0 到1 之间）作为参数，在一个圆上画出大小为0.05 且间距相等的N 个点，
    // 然后将每对点按照概率p 用灰线连接。
    /**
     * 画圆
     * @param x 圆心x坐标
     * @param y 圆心y坐标
     * @param r 半径r
     */
    private static void drawCircle(double x, double y, double r) {
        StdDraw.setXscale(0, 2 * x);
        StdDraw.setYscale(0, 2 * y);
        StdDraw.setPenRadius(0.003);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.circle(x, y, r);
    }

    /**
     * 在圆上描点
     * @param x0 圆心x坐标
     * @param y0 圆心y坐标
     * @param r 半径r
     * @param N N个点
     */
    private static double[][] drawPoints(double x0, double y0, double r, int N) {
        double[][] points = new double[N][2];
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        for(int idx = 0; idx < N; ++idx) {
            double x = x0 + r * Math.cos(2 * Math.PI * idx / N);
            double y = y0 + r * Math.sin(2 * Math.PI * idx / N);
            StdDraw.point(x, y);
            points[idx][0] = x;
            points[idx][1] = y;
        }
        return points;
    }

    /**
     * 以概率p随机连接顶点集points中的点
     * @param points    点集
     * @param p 概率p
     */
    private static void randomLinkPoints(double[][] points, double p) {
        StdDraw.setPenRadius(0.002);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        int length = points.length;
        for(int i = 0; i < length; ++i)
            for(int j = 0; j < length; ++j)
                if(true == StdRandom.bernoulli(p))
                    StdDraw.line(points[i][0], points[i][1], points[j][0], points[j][1]); // 应该再建立一个包含x坐标和y坐标的数据结构
    }

    /**
     * 在圆上画N个点然后每两点间以概率p连接
     * @param N N个点
     * @param p 概率p
     */
    private static void randomLink(int N, double p) {
        double x = 10.0;
        double y = 10.0;
        double r = 9.0;
        drawCircle(x, y, r);
        double[][] points = drawPoints(x, y, r, N);
        randomLinkPoints(points, p);
    }
    private static int binom_N = 100;

    private static int binom_k = 50;

    private static double[][] binom = new double[binom_N + 1][binom_k + 1];
    // 1.1.27 估计一下代码（100,50）的调用次数
    public static double binomial(int N, int K, double p, int count) {
        if (N < 0 || K < 0) return 0.0;
        if (N == 0 && K == 0) return 1.0;
        System.out.println(++count);
        return (1.0 - p) * binomial(N - 1, K, p, count) + p * binomial(N - 1, K - 1, 0, count);
    }

    // 1.1.24　 给出使用欧几里德算法计算105 和24 的最大公约数的过程中得到的一系列p 和q 的值。
    // 扩展该算法中的代码得到一个程序Euclid，从命令行接受两个参数，计算它们的最大公约数并打印出每次调用递归方法时的两个参数。
    // 使用你的程序计算1 111 111 和1 234 567 的最大公约数。
    public static int soutGcd(int p, int q) {
        if (0 == q) {
            System.out.printf("第一个参数%d,第二个参数%d \n", p, q);
            return p;
        }
        int r = p % q;
        System.out.printf("第一个参数%d,第二个参数%d \n", q, r);
        return gcd(q, r);
    }

    /**
     * @param p
     * @param q
     * @return 求两个数的最大公约数
     */
    public static int gcd(int p, int q) {
        if (0 == q) return p;
        int r = p % q;
        return gcd(q, r);
    }

    // 1.1.21
    // 从标准输入按行读取数据，其中每行都包含一个名字和两个整数。
    // 然后用printf() 打印一张表格，每行的若干列数据包括名字、两个整数和第一个整数除以第二个整数的结果，精确到小数点后三位。
    // 可以用这种程序将棒球球手的击球命中率或者学生的考试分数制成表格。
    public static void getTable() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String[]> data = new ArrayList<>();
        String breakStr = String.valueOf(0);
        while (true) {
            String[] res = new String[3];
            System.out.println("中断程序请按0,请输入姓名");
            res[0] = scanner.next();
            if (breakStr.equals(res[0])) break;
            System.out.println("请输入整数");
            res[1] = scanner.next();
            if (breakStr.equals(res[1])) break;
            System.out.println("请输入整数");
            res[2] = scanner.next();
            if (breakStr.equals(res[2])) break;
            System.out.printf("姓名:%s\t第一个数%s\t第二个数%s\t第三个小数%.3f\t", res[0], res[1], res[2], Double.parseDouble(res[1]) / Double.parseDouble(res[2]));
        }
    }

    // 1.1.20 编写一个递归的静态方法计算ln(N!) 的值。
    public static double lg2(int a) {
        // lgMN = lgM + LgN
        // -->lg(N!) = lg1+lg2....lgN =ln(N!)
        if (a < 0) return 0;
        // 调用Math.log（）+.....
        return Math.log(a) + lg2(a - 1);
    }

    // 1.1.14 输入一个数N，输出《= log2的最大整数
    public static int lg(int a) {
        // int count = 0, result = 1;
        // if (result > a) return count;
        // else {
        //     result *= 2;
        //     ++count;
        //     lg(a);
        // }
        // return 0;
        int count = 0, result = 1;
        while (result <= a) {
            result *= 2;
            ++count;
        }
        return --count;
    }

    // 1.1.13 转置二维数组 a[i][j] = b[j][i]
    public static String[][] transferMatrix(String[][] arr) {
        String[][] a = new String[arr[0].length][arr.length];
        for (int i = 0; i < a.length; i++) {
            String[] b = a[i];
            for (int j = 0; j < b.length; j++) {
                b[j] = arr[j][i];
            }
        }
        return a;
    }

    public static void soutMatrix(String[][] arr) {
        String[][] a = arr.clone();
        for (int i = 0; i < a.length; i++) {
            String[] b = a[i];
            for (int j = 0; j < b.length; j++) {
                System.out.printf("the ele is %s \n", b[j]);
            }
        }
    }

    public static String[][] generateMatrix(int rows, int columns) {
        String[][] a = new String[rows][columns];
        for (int i = 0; i < a.length; i++) {
            String[] b = a[i];
            for (int j = 0; j < b.length; j++) {
                b[j] = i + ">" + j;
            }
        }
        return a;
    }

    // 1.1.12
    public static void test() {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++)
            a[i] = 9 - i;
        // [9,8,7,6,5,4,3,2,1,0]
        for (int i = 0; i < 10; i++) {
            a[i] = a[a[i]];
            // a[0] = a[9] -> a[0] = 0
            // a[1] = 1
            // a[2] = 2
            // a[3] = 3
            // a[4] = 4
            // a[5] = a[4] = 4
            // ...
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(a[i]);
        }
    }
}
