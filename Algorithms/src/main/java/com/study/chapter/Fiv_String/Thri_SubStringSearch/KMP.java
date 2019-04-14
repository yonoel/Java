package com.study.chapter.Fiv_String.Thri_SubStringSearch;

public class KMP {
    public static void main(String[] args) {
        // String txt = "AABRAACADABRAACAADABRA";
        // String pat = "AACAA";
        // KMP kmp = new KMP(pat);
        // System.out.println("text is :" + txt);
        // int offset = kmp.search(txt);
        // System.out.print("pat is  :");
        // for (int i = 0; i < offset; i++) {
        //     System.out.print(" ");
        // }
        // System.out.println(pat);
        getNext("abaabbabaab");
        // getNext2("abaabbabaab");
    }

    public static void getNext2(String pat) {
        int m = pat.length();
        char[] B = pat.toCharArray();
        int[] F = new int[m];
        for (int i = 1; i < m; i++) {
            int j = F[i - 1];
            while ((B[j + 1] != B[i]) && (j >= 0))
                j = F[j];
            if (B[j + 1] == B[i])
                F[i] = j + 1;
            else
                F[i] = -1;
        }
    }

    // 得到状态机的另一种实现
    public static int[] getNext(String ps) {

        char[] p = ps.toCharArray();

        int[] next = new int[p.length];
        // d第一次不匹配时回退-1
        next[0] = -1;

        int j = 0;

        int k = -1;

        while (j < p.length - 1) {

            if (k == -1 || p[j] == p[k]) {

                next[++j] = ++k;

            } else {

                k = next[k];

            }

        }

        return next;

    }

    // different array 第一个下标是字符char，第二个长度是pattern的长度，
    private int[][] dfa;
    private int M;
    private String pat;

    public KMP(String pat) {
        this.pat = pat;
        M = pat.length();
        int R = 256;
        dfa = new int[R][M];// [256][pat.length]
        dfa[pat.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                // 初始化dfa
                dfa[c][j] = dfa[c][x];
            }
            char c = pat.charAt(j);
            dfa[c][j] = j + 1;
            x = dfa[c][x];
        }
    }

    public int search(String txt) {
        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            int d = txt.charAt(i);
            j = dfa[d][j];
        }
        if (j == M) return i - M;
        else return N;
    }


}
