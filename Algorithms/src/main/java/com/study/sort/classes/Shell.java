package com.study.sort.classes;

public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        /*//  要去分组的增量，也就是分组的跨度
        int h = 1;
        // 初始化了增量的值
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                // 将a[i]插到a[i-h]
                for (int j = i; j >= h && Example.less(a[j],a[j-h]); j -= h) {
                    Example.exch(a,j,j-h);
                }
            }
            h = h / 3;
        }*/
        int d, i, j,n=N; //d为增量
        for(d = n/2;d >= 1;d = d/2) //增量递减到1使完成排序
        {
            for(i = d; i < n;i++)   //插入排序的一轮
            {
                // 初始情况就是 j = 0,
                for(j = i - d;(j >= 0) && Example.less(a[j+d],a[j]);j = j-d)
                {
                  Example.exch(a,j+d,j);
                }

            }
        }
    }
}
