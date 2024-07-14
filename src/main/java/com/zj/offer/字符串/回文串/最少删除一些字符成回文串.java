package com.zj.offer.字符串.回文串;

/**
 * @Author: Created by com.zj
 * @Date: 2021/08/27 上午 08:04
 * 题目：
 * 给定一个字符串str，可以从中删除一些字符串，
 * 使得剩下的字符串成为一个回文字符串，
 * 如何保证删除之后的字符串位最长，求出需要删除的字符个数
 */
public class 最少删除一些字符成回文串 {
    public static void main(String[] args) {
        String str = "ab3bd";

        System.out.println(deleteSomeCharByString(str));
    }

    /**
     * 思路：
     * 二维表dp[][]表示子串str[i...j]需要最少删除几个字符可以使得新的字符串位回文字符串。
     * 考虑简答情况，如果str长度等于1，那么不需要删除，即删除个数为0；
     * 如果远字符串长度为2，那么任意删除一个，就变为回文串了；
     * 如果长度大于2，
     * 若str[i] == str[j],那么dp[i][j] = 0，表示已经是一个回文串，不需要删除，
     * 如果str[i]≠str[j]，则需要删除。具体实现方式为，
     * 将str[i+1][j]变为回文串或者将str[i][j-1]变为回文串，比较两种方式代价小的，
     * 然后更新dp[i][j]=min(dp[i+1][j]+dp[i][j-1]) + 1;
     */

    public static int deleteSomeCharByString(String str) {

        int[][] dp = new int[str.length()][str.length()];
        for (int i = 1; i < str.length(); i++) {
            // 前后字符比较
            // 如果相同的话，不需要删除，
            // 如果不相同，需要删除一个字符才能为回文串
            if (str.charAt(i) == str.charAt(i - 1)) {
                dp[i - 1][i] = 0;
            } else {
                dp[i - 1][i] = 1;
            }
            for (int j = i - 2; j > -1; j--) {
                if (str.charAt(j) == str.charAt(i)) {
                    dp[j][i] = dp[j - 1][i + 1];
                } else {
                    dp[j][i] = Math.min(dp[j + 1][i], dp[j][i - 1]) + 1;
                }
            }
        }

        return dp[0][str.length() - 1];
    }
}
