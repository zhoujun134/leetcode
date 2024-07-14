package com.zj.算法思想.动态规划;

/**
 * @Author: zhoujun
 * @Date: 2021/3/26 9:38
 * @Description:
 * 给定一个大小为n的数组 prices 代表连续n天某支股票的股价，
 * prices[i]即第i天的股价，且必须在买进后才能卖出。
 * 再给定一些限制条件，问最大收益。
 * 示例：
 *      输入：[1,4,2]
 *      输出： 3
 */
public class O1买卖股票一次 {

    public static void main(String[] args) {
        int[] nums = {1, 4, 2};
        System.out.println(maxProfit(nums));
    }

    public static int maxProfit (int[] prices) {
        // write code here
        int minn = prices[0];
        int ans = 0;
        for (int price : prices) {
            minn = Math.min(minn, price);
            ans = Math.max(ans, price - minn);
        }
        return ans;
    }
}
