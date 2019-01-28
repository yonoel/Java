package com.study.chapter.Fir_Base;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

public class Test {
    public static void main(String[] args) {

//        for (int i = 0; i < 10; i++)
//            System.out.println(i);

        // new com.chapter.Fir_Base.Test().numUniqueEmails(new String[]{
        //         "test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com", "teste+mail+david@lee.tcode.com"
        // });
        // com.chapter.Fir_Base.StopWatch stopWatch = new com.chapter.Fir_Base.StopWatch();
        // int N = 100000000;
        // int sum = 0;
        // for (int i = 0; i > 0; i/=2) {
        //     for (int j = 0; j < i; j++) {
        //         sum++;
        //     }
        // }
        // double time = stopWatch.elapsedTime();
        // System.out.printf("N is %d,time is %8.5f",N,time);
        // System.out.printf("k's value:%d",k);
        // shuffle(new int[]{11,12,12,13});
        // drawPie(10, 0.1);
        // com.chapter.Fir_Base.Test.test1();
        // com.chapter.Fir_Base.Test.test2();
        // com.chapter.Fir_Base.Test.test3();
        // com.chapter.Fir_Base.Test.test4();
        // com.chapter.Fir_Base.Test.test5();
        // com.chapter.Fir_Base.Test.test6();
        // System.out.println(com.chapter.Fir_Base.Test.lg(5));
        // int[] a = {1, 2, 1, 1, 1,};
        // Arrays.stream(histogram(a, 7)).forEach(b -> {
        //
        //     System.out.println(b + "");
        // });
        //  Arrays.stream(a).forEach(b->{
        //      System.out.println(b);
        //  });
        // numJewelsInStonesBoolean("aA", "aAAbbbb")
        // new com.chapter.Fir_Base.Test().sortArrayByParity(new int[]{3, 1, 2, 4});
        // new com.chapter.Fir_Base.Test().uniqueMorseRepresentations(new String[]{
        //         "gin"
        // });

    }

    public boolean judgeCircle(String moves) {
        //太慢了，用char会快很多很多
        int[] move = {0, 0};
        String[] str = moves.split("");
        for (int i = 0; i < str.length; i++) {
            if ("U".equals(str[i])) move[1]++;
            else if ("D".equals(str[i])) move[1]--;
            else if ("L".equals(str[i])) move[0]--;
            else if ("R".equals(str[i])) move[0]++;
        }
        return move[0] == 0 && move[1] == 0;
    }

    public int[] sortArrayByParity(int[] A) {
        int i = 0, j = A.length - 1;
        while (i < j) {
            if (A[i] % 2 > A[j] % 2) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

            if (A[i] % 2 == 0) i++;
            if (A[j] % 2 == 1) j--;
        }

        return A;
        // int[] ans = new int[A.length];
        // int t = 0;
        //
        // for (int i = 0; i < A.length; ++i)
        //     if (A[i] % 2 == 0)
        //         ans[t++] = A[i];
        //
        // for (int i = 0; i < A.length; ++i)
        //     if (A[i] % 2 == 1)
        //         ans[t++] = A[i];
        //
        // return ans;
        // return Arrays.stream(A)
        //         .boxed()
        //         .sorted((a, b) -> Integer.compare(a%2, b%2))
        //         .mapToInt(i -> i)
        //         .toArray();
    }

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();
        // 初始化一个保存字母对应的morse
        // Map<String, String> morse = new HashMap<>();
        String[] values = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            //  把一个word转换成morse
            char[] arr = word.toCharArray();
            for (char c : arr) {
                //    都是小写
                sb.append(values[c - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }

    public String toLowerCase(String str) {
        // return str.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c >= 65 && c <= 90) {
                c = (char) (c + 32);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public int numUniqueEmails(String[] emails) {
        // 暴力解法
        //    对数组中每一个元素取正取的值
        //    然后放到set里，取set的size
        // Set<String> set = new HashSet<String>();
        // for (String str : emails) {
        //     String email = Stream.of(str.split(".com")[0].split("")).reduce("",
        //             new BinaryOperator<String>() {
        //                 @Override
        //                 public String apply(String s, String s2) {
        //                     if (!"+".equals(s2) && !".".equals(s2))
        //                         return s.concat(s2);
        //                     else return s;
        //                 }
        //             });
        //     email += ".com";
        //     set.add(email);
        // }
        // return set.size();
        return 0;
    }

    /**
     * 利用了Ascii表默认位置，
     *
     * @param J
     * @param S
     * @return
     */
    public static int numJewelsInStonesBoolean(String J, String S) {
        // 初始化了一个z-A的boolean数组
        boolean[] dict = new boolean['z' - 'A' + 1];
        for (int i = 0; i < J.length(); i++) {
            // charAt（）返回了字符然后减去‘A’，得到位置，再把数组里的值改变。。。
            dict[J.charAt(i) - 'A'] = true;
        }
        int res = 0;
        for (int i = 0; i < S.length(); i++) {
            //同理，如果dist里这个位置为真，则++
            if (dict[S.charAt(i) - 'A']) res++;
        }
        return res;
    }

    public static int numJewelsInStones(String J, String S) {
        String[] jArr = J.split("");
        int index = -1;
        int count = 0;
        //    暴力解法，直接两个循环
        for (int i = 0; i < jArr.length; i++) {
            String search = new String(S);
            while ((index = search.indexOf(jArr[i])) != -1) {
                count++;
                search = search.substring(index + 1);
            }
        }
        return count;
    }


    /**
     * 斐波那契数列
     */
    public static void test1() {
        int f = 0;
        int g = 1;
        for (int i = 0; i < 15; i++) {
            System.out.println(f);
            f += g;
            g = f - g;
        }
    }

    public static void test2() {
        System.out.println(.001);
        double b = 9.0;
        while (Math.abs(b - 9.0 / b) > .001) {
            System.out.printf("before,%.5f\n", b);
            b = (9.0 / b + b) / 2.0;
            System.out.printf("after,%.5f\n", b);

        }
    }

    public static void test3() {
        int sum = 0;
        for (int i = 1; i < 1000; i++) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
            System.out.println(sum);
        }
    }

    public static void test4() {
        int sum = 0;
        for (int i = 1; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                sum++;
            }
            System.out.println(sum);
        }
    }

    public static void test5() {
        System.out.println('b');
        System.out.println('b' + 'c');//acsii 197
        System.out.println((char) ('a' + 4));
    }

    public static void test6() {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = 9 - i;
        }
        for (int i = 0; i < 10; i++) {
            a[i] = a[a[i]];
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(a[i]);
        }
    }

    /**
     * 打印出一个M行N列的二维数组，交换行和列
     */
    public static void test7() {
        int m = 10;
        int n = 5;
        int[][] a = new int[m][n];
        int[][] b = new int[n][m];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                b[j][i] = a[i][j];
            }
        }
    }

    /**
     * 给定参数n，返回x = log2N
     */
    public static int lg(int n) {
        int reVal = 0;
        if (2 > n) return reVal;
        reVal = 1;
        int m = 2;
        while (n > m) {
            m = m * 2;
            reVal++;
        }
        return reVal;
    }

    /**
     * 接受一个数组a和一个参数m
     *
     * @return 返回一个m大小的数组，
     * 1.其中第i个元素值为整数i在a中出现的次数
     * a[]中的值为0到m-1中，返回数组的元素和应等于a.length
     */
    public static int[] histogram(int[] a, int m) {
        int[] reVal = new int[m];
        boolean flag = true;
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < a.length; j++) {
                if (a[j] == i) count++;
                if (a[j] >= m) flag = false;
            }
            reVal[i] = count;
        }

        return reVal;
    }

    /**
     * @desciption N*N的二维数组，如果i和j互质，则[i][j] = true
     */
    public static void test8(int n) {
        boolean[][] arr = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = i % j == 0 ? false : true;
            }
        }
    }

    /**
     * @param k
     * @param d 在一个圆上画出大小为0.05且间距相等的N个点，然后将每对点按照概率d用灰线连接。
     */
    public static void drawPie(int k, double d) {
        int N = k;
        double P = d;
        double[][] points = new double[N][2];

        StdDraw.setXscale(0, 500);
        StdDraw.setYscale(0, 500);

        double Ox = 250;
        double Oy = 250;
        double r = 200;

        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.point(Ox, Oy);
        StdDraw.circle(Ox, Oy, r);

        StdDraw.setPenRadius(0.008);
        StdDraw.setPenColor(StdDraw.GRAY);
        for (int i = 0; i < N; i++) {
            points[i][0] = Ox + Math.cos(i * 2 * Math.PI / N) * r;
            points[i][1] = Oy + Math.sin(i * 2 * Math.PI / N) * r;
        }

        for (int i = 0; i < points.length; i++)
            for (int j = 0; j < points.length; j++)
                if (StdRandom.bernoulli(P))
                    StdDraw.line(points[i][0], points[i][1], points[j][0], points[j][1]);
    }

    /**
     * @description 把一个数组打乱
     */
    public static int[] shuffle(int[] arr) {

        // // 1.随机根据下标取数 2.原数组去掉当前位置，3.循环往复，直到没有元素 卡在数组去除上的消耗
        // Random random = new Random();
        // int length = arr.length;
        // int[] arrCopy = Arrays.copyOf(arr,length);
        // int[] result = new int[length];
        // for (int i = 0; i < length; i++) {
        //     int r = random.nextInt(length) + 1 ;
        //     result[i] = arrCopy[r];
        //     arrCopy = arrCopy.remove();
        // }
        // 1.随机一个数（源数组长度），把源数组最后的一个值赋予 返回数组的随机数位置，然后随机次数+1，
        // 2.把源数组中
        // 3.随机一个数的范围 -1 再次重复 1的操作
        int runCount = 0;
        int[] arr2 = new int[arr.length];
        int length = arr.length;
        int randomCount = 0;
        int index = 0;
        int k = 0;
        do {
            runCount++;
            Random random = new Random();
            int r = length - randomCount;
            index = random.nextInt(r);//随机index的范围根据长度-随机过的次数的长度
            arr2[k++] = arr[index];
            randomCount++;
            arr[index] = arr[r - 1];//将最后一个的值交换随机出来的index的值
        } while (randomCount < length);
        System.out.printf("运行次数:%d", runCount);
        return arr2;
    }

    /**
     * @description 计算两个骰子之和的概率分布
     */
    public static double[] sides() {
        int sides = 6;
        double[] dist = new double[2 * sides + 1];
        for (int i = 1; i <= sides; i++) {
            for (int j = 1; j <= sides; j++) {
                dist[i + j] += 1.0;
            }
        }
        for (int i = 2; i <= 2 * sides; i++) {
            dist[i] /= 36.0;
        }
        return dist;
    }
}
