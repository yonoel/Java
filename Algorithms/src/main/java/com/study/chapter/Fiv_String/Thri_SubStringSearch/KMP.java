package com.study.chapter.Fiv_String.Thri_SubStringSearch;

public class KMP {
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
                dfa[c][j] = dfa[c][x];
            }
            int d = pat.charAt(j);
            dfa[pat.charAt(j)][j] = j + 1;
            x = dfa[pat.charAt(j)][x];
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

    public static void main(String[] args) {
        String txt = "AABRAACADABRAACAADABRA";
        String pat = "AACAA";
        KMP kmp = new KMP(pat);
        System.out.println("text is :"+ txt);
        int offset = kmp.search(txt);
        System.out.print("pat is  :");
        for (int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pat);
    }
}
