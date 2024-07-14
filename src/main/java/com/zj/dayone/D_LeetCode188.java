package com.zj.dayone;

/**
 * @author junzhou
 * @date 2020/12/28 21:44
 * @description:
 * 给定一个整数数prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1：
 *
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 *
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 * @since 1.8
 */
public class D_LeetCode188 {
    /**
     * @param k
     * @param prices
     * @return
     */
    public static int maxProfit(int k, int[] prices) {
        int res = d(0, 0,false, k, prices);

        return res;

    }

    /***
     *
     * 递归模拟过程，天数i，交易次数j，有无股票s
     *          无股票：max( 观望, 买入股票，现金 -= 当日股票价格 ）
     *          有股票：max( 持有，卖出股票，现金 += 当日股票价格，交易次数 += 1 )
     * 边界：交易次数 = k 或 数组越界
     * 
     * @param i
     * @param j
     * @param s
     * @param k
     * @param prices
     * @return
     */
    public static int d(int i, int j, boolean s, int k, int[] prices){
        if(j==k || i==prices.length){
            return 0;
        }
        else {
            if (s){
                return Math.max(d(i+1, j, s, k, prices), d(i+1, j+1, false, k, prices) + prices[i]);
            }else{
                return Math.max(d(i+1, j, s, k, prices), d(i+1, j, true, k, prices) - prices[i]);
            }

        }
    }
    public static void main(String[] args) {
        int k=2;
        int[] prices = {3,2,6,5,0,3};

        System.out.println(maxProfit(k, prices));
    }



}
