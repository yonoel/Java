package com.study.chapter.Fiv_String.Thri_SubStringSearch;

/**
 * @program: Java
 * @description: 从模式最右开始匹配
 * @author: Qian Yi Zhen
 * @create: 2019/04/17
 */
public class BoyerMoore {
    public static void main(String[] args) {
        BoyerMoore boyerMoore = new BoyerMoore("NEEDLE");
        String txt = "FINDINATLESTACKNEEDLE";
        System.out.println(boyerMoore.search(txt));
    }

    private int[] right;//保存向右的偏移量,下标是该字符在字母表中的index
    private String pat;

    public BoyerMoore(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];
        for (int i = 0; i < R; i++) {
            // -1 不存在
            right[i] = -1;
        }
        for (int i = 0; i < M; i++) {
            int index = pat.charAt(i);
            right[index] = i;
        }
    }

    public int search(String txt) {
        int N = txt.length(), M = pat.length(), skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            // 从右向左
            for (int j = M - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    // 比如 NEEDLE j = 1,但是 right[txt.charAt(i + j)=E] = 5 skip = -4 就是左偏，没有意义
                    if (skip < 1) skip = 1;
                    break;
                }
            }
            if (skip == 0) return i; // 找到匹配了
        }
        return -1;
    }
}
