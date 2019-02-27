package com.study.chapter.Six_Context;

import java.util.Arrays;

public class SuffixArray {
    private final String[] suffixes;
    private final int N;

    public SuffixArray(String text) {
        N = text.length();
        suffixes = new String[N];
        for (int i = 0; i < N; i++) {
            suffixes[i] = text.substring(i);
        }
        Arrays.sort(suffixes);
    }

    public int length() {
        return N;
    }

    public String select(int i) {
        // 后缀数组中第i个元素
        if (i > length()) return null;
        return suffixes[i];
    }

    public int index(int i) {
        return N - suffixes[i].length();
    }

    public int lcp(int i) {
        String last = select(i);
        if (last != null) {
            int n = 0;
            for (int j = 1; j < i; j++) {
                String a = suffixes[i];
                String b = suffixes[i - 1];
                int k = 0;
                int M = Math.min(a.length(), b.length());
                for (int l = 0; l < M; l++) {
                    if (a.charAt(l) == b.charAt(l)) {
                        k++;
                    } else {
                        break;
                    }
                }
                if (k > n) {
                    n = k;
                }
            }
            return n;
        }
        return -1;
    }

    // 小于key的后缀数量
    public int rank(String key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(suffixes[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }
}
