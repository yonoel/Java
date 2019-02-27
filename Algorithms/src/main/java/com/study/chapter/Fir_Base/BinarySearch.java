package com.study.chapter.Fir_Base;
/******************************************************************************
 *  Compilation:  javac com.chapter.Fir_Base.BinarySearch.java
 *  Execution:    java com.chapter.Fir_Base.BinarySearch whitelist.txt < input.txt
 *  Dependencies: In.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/11model/tinyW.txt
 *                https://algs4.cs.princeton.edu/11model/tinyT.txt
 *                https://algs4.cs.princeton.edu/11model/largeW.txt
 *                https://algs4.cs.princeton.edu/11model/largeT.txt
 *
 *  % java com.chapter.Fir_Base.BinarySearch tinyW.txt < tinyT.txt
 *  50
 *  99
 *  13
 *
 *  % java com.chapter.Fir_Base.BinarySearch largeW.txt < largeT.txt | more
 *  499569
 *  984875
 *  295754
 *  207807
 *  140925
 *  161828
 *  [367,966 total values]
 *
 ******************************************************************************/



public class BinarySearch {
    public static void main(String[] args) {

        // int a = rank(1,new int[]{1,1,1,1,2,3,4,5,6});
        // System.out.println(a);
    }

    /**
     * This class should not be instantiated.
     */
    private BinarySearch() {
    }

    /**
     * @param key
     * @param arr
     * @return 返回小于该key的同时属于arr的元素数量
     */
    public static int rank(int key, int[] arr) {
        // 数组必须有序
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            //    要么不存在，要么在arr[lo..hi]之间
            int mid = lo + (hi - lo) / 2;

            if (key < arr[mid]) hi = mid - 1;
            else if (key > arr[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
        //  调用二叉树搜索明显更快；
      /*  int count = 0;
        int[] arrSplit = arr;
        int a = -1;
        while ((a = indexOf(arrSplit,key)) != -1) {
            count++;
            if( a == arr.length -1 ) {
                break;
            }
            arrSplit = Arrays.copyOfRange(arr,a+1,arr.length);
        }
        return count;*/
    }

    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param a   the array of integers, must be sorted in ascending order
     * @param key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}
