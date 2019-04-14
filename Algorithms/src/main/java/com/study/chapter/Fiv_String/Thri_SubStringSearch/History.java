package com.study.chapter.Fiv_String.Thri_SubStringSearch;

public class History {

    /**
     * ---CREATE LOG---
     *
     * @Description 最差情况下 需要(N-M+1) * M 次交换
     * @MethodName
     * @Parm
     * @Return
     * @Author Qian Yi Zhen
     * @Date ------------
     **/
    /**
     * 自己搞的暴力解法
     */
    public static int searchO(String pat, String txt) {
        int N = txt.length();
        int m = pat.length();
        for (int i = 0; i < N - m; i++) {
            // 如果匹配成功那么，j == M ，且i也向后偏移了M
            int j;
            for (j = 0; j < m; j++) {
                // 对于txt而言，它提取出来的字符既是 i + j
                if (txt.charAt(i + j) != pat.charAt(j)) break;
            }
            if (j == m) return i;
        }
        return N;
    }



    // 暴力查找的另一种实现
    public static int searchVersion2(String pat, String txt) {
        int j, M = pat.length(), i, N = txt.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            // 如果字符匹配，j++
            if (txt.charAt(i) == pat.charAt(j)) j++;
            else {
                // 如果不匹配，直接回退i
                i -= j;
                j = 0;
            }
        }
        if (j == M) return i - M;// 找到匹配
        else return N;// 未找到匹配
    }

    public static void main(String[] args) {
        searchVersion2("aa", "asdafsadsbfsghsaaasfdsafa");
    }
}
