package com.study.chapter.Fiv_String.Thri_SubStringSearch;

import com.study.chapter.Fir_Base.ThirdSection_Collection.Queue;

import java.math.BigInteger;
import java.util.Random;

/**
 * @program: Java
 * @description: 基于散列的字符串查找
 * @author: Qian Yi Zhen
 * @create: 2019/04/17
 */
public class RobinKarp {

    // horner函数 用于计算hash值，有些字符串的值比较长
    private long hash(String key, int M) {
        //计算 key[0...M-1]的hash值
        long h = 0;
        for (int i = 0; i < M; i++) {
            h = (R * h + key.charAt(i)) % Q;
        }
        return h;
    }

    private String pat;
    private long patHash;
    private int M;
    private int R = 256;
    private long Q;
    private long RM; // R^(M-1) % Q

    public RobinKarp(String pat) {
        this.pat = pat;
        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for (int i = 1; i <= M - 1; i++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);
    }
    // 5.3.33 随机N位数字是素数的概率与 1/n成正比-->目前来说大素数随机生成方法是不存在的，
    private long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    private int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (patHash == txtHash && check(0)) return 0; // 一开始匹配成功
        for (int i = M; i < N; i++) {
            // 减去第一个数，加上最后一个数，再次匹配
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash)
                if (check(i - M + 1)) return i - M + 1;
        }
        return -1;
    }

    public boolean check(int i) {
        // 蒙特卡洛算法
        return true;
    }

}
