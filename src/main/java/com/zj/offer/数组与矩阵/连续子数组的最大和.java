package com.zj.offer.数组与矩阵;

/**
 * @Author: Created by com.zj
 * @Date: 2021/10/09 上午 11:21
 * 输入一个长度为n的整型数组a，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为 O(n).
 * 输入: [1,-2,3,10,-4,7,2,-5]
 * 输出: 18
 * 说明: 输入的数组为{1,-2,3,10,-4,7,2,-5}，和最大的子数组为{3,10,-4,7,2}，因此输出为该子数组的和 18。
 */
public class 连续子数组的最大和 {

    public static void main(String[] args) {
//        int[] array = {1,-2,3,10,-4,7,2,-5};
//        int[] array = {-1,-2,-3,-4,5};
        int[] array = {-2,-8,-1,-5,-9};
//        findGreatestSumOfSubArray(array);
        System.out.println(findGreatestSumOfSubArray2(array));

    }

    /**
     * 方法一: 暴力求解
     * 时间复杂度 O(n^2)
     * @param array 需要计算的数组
     * @return 连续子数组的最大和
     */
    public static int findGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0){
            return 0;
        }
        if (array.length == 1){
            return array[0];
        }

        int resultMax = array[0];
        for (int i = 0; i < array.length; i++) {
            int tempSum = array[i];
            if (tempSum > resultMax){
                resultMax = tempSum;
            }
            for (int j = i + 1; j < array.length; j++) {
                tempSum += array[j];
                // 如果大于就将 tempSum 值付给 max
                if (tempSum > resultMax){
                    resultMax = tempSum;
                }
                // 如果小于 max, 则继续循环
            }
        }
        return resultMax;
    }

    /**
     * 方法二，动态规划求解
     * @param array 待求解的数组
     * @return 返回连续子数组最大和
     */
    public static int findGreatestSumOfSubArray2(int[] array) {
        if (array == null || array.length == 0){
            return 0;
        }
        if (array.length == 1){
            return array[0];
        }
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int resultMax = dp[0];
        for (int i = 1; i < array.length; i++) {
            int temp = dp[i-1] + array[i];
            dp[i] = Math.max(temp, array[i]);
            if (dp[i] > resultMax){
                resultMax = dp[i];
            }

        }
        return resultMax;
    }
}
