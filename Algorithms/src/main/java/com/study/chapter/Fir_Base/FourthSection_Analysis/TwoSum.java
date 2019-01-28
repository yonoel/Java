package com.study.chapter.Fir_Base.FourthSection_Analysis;
// 级别 N^2
public class TwoSum {
    public static int count(int[] arr){
        int count = 0;
        for (int i = 0; i <arr.length ; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] + arr[j] ==0 )count++;
            }

        }
        return count;
    }
}
