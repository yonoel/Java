package com.study.chapter.first.collection;

public class Josephus {
    private class Person {
    }

    /**
     * 假设有n个人围坐一圈，现在要求从第k个人开始报数，报到第m个数的人退出。
     * 然后从下一个人开始继续报数并按照同样的规则退出，直至所有人都退出。
     */
    int n = 50;
    Person[] people = new Person[n];
    int k = 0;
    int m = 6;

    public void fn1() {
        //  第一次 0 - (n-1)
        //  这个挂了 people[k + m - 1], people[n-1];余下的是 0 -- k+m-2,k+m-2  --n
        //  第二次
        //  这个挂了  people[k + m - 1+ m] -> people[n-1 -1 ];
        //  明显就有一个问题了，首先  k <= n,其次people应该是一个环形，否则k+m会大于这个数组
        //  如果 k +  m - 1 > n 那么，正确的下标应该是 k + m - 1 - n
        //  结论，活下去的人数是m-1
        int rest = n;
        while (rest != 1){
            // 开始报数需要去掉的人
             int dead = k + m - 1;
             if(dead > rest){ dead = dead - rest - 1;}
            // 新的数组就是
            rest --;
        }
    }
}
