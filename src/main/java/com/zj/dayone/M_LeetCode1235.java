package com.zj.dayone;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.cn/problems/maximum-profit-in-job-scheduling/">题目地址</a>
 */
public class M_LeetCode1235 {


    /**
     * 动态规划 + 二分查找
     * 我们首先将兼职工作按结束时间 endTime 从小到大进行排序。使用 dp[i] 表示前 i 份兼职工作可以获得的最大报酬，
     * 初始时 dp[0]=0，那么对于 i>0，我们有以下转移方程：
     *              dp[i]=max(dp[i−1],dp[k]+profit[i−1])
     * 其中 k 表示满足结束时间小于等于第 i - 1 份工作开始时间的兼职工作数量，可以通过二分查找获得。
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param profit 对应时间段的总报酬
     * @return 最高的报酬
     */
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[1]));
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int k = binarySearch(jobs, i - 1, jobs[i - 1][0]);
            dp[i] = Math.max(dp[i - 1], dp[k] + jobs[i - 1][2]);
        }
        return dp[n];
    }

    public static int binarySearch(int[][] jobs, int right, int target) {
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (jobs[mid][1] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {

    }
}
