package com.study.chapter.Fir_Base.FourthSection_Analysis;

import com.study.chapter.Fir_Base.BinarySearch;

import java.util.Arrays;
//级别 NlgN
public class TwoSumFast {
    public static int count(int[] a) {
        Arrays.sort(a);
        int N = a.length, cnt = 0;
        for (int i = 0; i < N; i++) {
            if (BinarySearch.rank(-a[i], a) > i) cnt++;
        }
        return cnt;
    }
}
