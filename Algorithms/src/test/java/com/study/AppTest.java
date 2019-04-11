package com.study;


//import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
//    @Test
    public void shouldAnswerWithTrue() {
    }

    public static void main(String[] args) {
        test();
    }

    /**
     * ---CREATE LOG---
     *
     * @Name test
     * @Params []
     * @Return void
     * @Author Qian Yi Zhen
     * @Date 2019-04-09
     * @Description 测试行列式的相加原则，即两个一模一样的行列式，就第i行不同，若两者相加，其他值不变，唯独这i行值改变的一个新的行列式
     * ------------
     **/
    public static void test() {
        int[][] a1 = new int[4][4];
        int[][] a2 = new int[4][4];
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a1[i].length; j++) {
                if (i == 2) {
                    a1[i - 1][j]++;
                }
                if (i == 3) {
                    a2[i - 2][j]++;
                }
            }
        }
    }
}

