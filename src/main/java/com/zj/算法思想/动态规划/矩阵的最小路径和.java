package com.zj.算法思想.动态规划;

/**
 * @Author: zhoujun
 * @Date: 2021/4/1 11:14
 * @Description:
 * 题目描述
 * 给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，路径上所有的数字累加起来就是路径和，输出所有的路径中最小的路径和。
 *
 * 题解
 * 第一行 只能从左往右
 * 第一个元素 的值为 原数组的第一个元素 dp[0][0] = a[0][0]
 * dp[0][j] = a[0][j] + dp[0][j-1];
 * 第一列元素 只能从上往下
 * dp[i][0] = dp[i-1][0] + a[i][0]
 *
 * 第二行第二列元素的可能从 当前节点的左节点 和上节点过来
 * 那么该节点的最小值应为 当前节点的值 加上 min （ 上节点 左节点）
 * dp[i][j] = a[i][j] + Math.min(dp[i][j-1],dp[i-1][j]);
 *
 * 那么最后一个节点的值就为最小的路径和
 *
 */
public class 矩阵的最小路径和 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,5,9},
                {8,1,3,4},
                {5,0,6,1},
                {8,8,4,0}
        };
        System.out.println(minPathSum(matrix));
    }
    public static int minPathSum (int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0][0] = matrix[0][0];
        // 第一行 只能从左往右
        for (int i = 1; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i] + dp[0][i-1];
        }
        // 第一列 只能从上往下
        for (int i = 1; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0] + dp[i-1][0];
        }
        // 非第一行第一列
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                dp[i][j] = matrix[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[matrix.length-1][matrix[0].length - 1];
    }
}
