package com.zj.offer.数组与矩阵;

import java.util.HashSet;

/**
 * @author junzhou
 * @date 2021/3/14 16:58
 * @description: 按顺时针的方向，从外到里打印矩阵的值。下图的矩阵打印结果为：1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10
 * @since 1.8
 */
public class 顺时针打印矩阵 {

    public static HashSet<Integer> printMatrix(int[][] matrix) {
        HashSet<Integer> ret = new HashSet<Integer>();
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            // 上
            for (int i = c1; i <= c2; i++) {
                ret.add(matrix[r1][i]);
            }
            // 右
            for (int i = r1 + 1; i <= r2; i++) {
                ret.add(matrix[i][c2]);
            }
            if (r1 != r2) {
                // 下
                for (int i = c2 - 1; i >= c1; i--) {
                    ret.add(matrix[r2][i]);
                }
            }
            if (c1 != c2) {
                // 左
                for (int i = r2 - 1; i > r1; i--) {
                    ret.add(matrix[i][c1]);
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ret;
    }


    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        HashSet<Integer> integers = printMatrix(matrix);
        for (int i : integers
        ) {
            System.out.print(" " + i);
        }


    }
}
