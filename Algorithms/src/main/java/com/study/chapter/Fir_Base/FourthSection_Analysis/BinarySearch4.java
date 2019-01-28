package com.study.chapter.Fir_Base.FourthSection_Analysis;


// 1.4.10 修改二分查找，使之返回和被查找的键匹配的最小的元素，保证对数级别
public class BinarySearch4 {
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        int temp = -1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                // mid = key but 说不定还有更小的
                temp = mid;
                hi = mid - 1;
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,4,4,4,4,4,5,6,7,8,9};
        BinarySearch4.indexOf(a,4);
    }
}
