package com.study.chapter.Fir_Base;

public class Gcd {
    public static void main(String[] args) {
        // System.out.println(gcd(32, 354));
        System.out.println("最大公约数:"+test(1111111,1234567));
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

    /**
     * @return 求两个数的最大公约数 /并打印调用时的两个参数
     */
    public static int test(int p, int q) {
        if (0 == q) {
            // System.out.printf("第一个参数%d,第二个参数%d/n", p, q);
            return p;
        }
        int r = p % q;
        System.out.printf("第一个参数%d,第二个参数%d\n", q, r);
        return gcd(q, r);
    }
}
