package com.zj.offer.字符串.回文串;

/**
 * @Author: zhoujun
 * @Date: 2021/3/29 15:12
 * @Description:
 */
public class 最长回文字符串 {

    public static void main(String[] args) {
//        System.out.println(getLongestPalindrome("abc1234321ab", 12));
        System.out.println(getLongestPalindrome2("abcbaasa", 5));
    }

    /**
     * 方法一 暴力破解
     *
     * @param A 字符串 A
     * @param n 字符串长度
     * @return
     */
    public static int getLongestPalindrome(String A, int n) {
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String now = A.substring(i, j);
                if (isPalindrome(now) && now.length() > maxLen) {
                    maxLen = now.length();
                }
            }
        }
        return maxLen;
    }

    // 判断子串是不是回文字符串
    public static boolean isPalindrome(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法二
     * 中心扩散法
     */
    public static int getLongestPalindrome2(String A, int n) {
        if (n == 0) return 0;
        int maxLen = 1;
        //中心枚举到n-2位置
        for (int i = 0; i < n - 1; i++) {// 比较以i为中心扩散的回文子串 && 以i和i+1为中心扩散的回文子串 哪个大取哪个
            int len = Math.max(centerSpread(A, i, i), centerSpread(A, i, i + 1));
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }

    //若left==right 则扩散中点为一个，此时的回文子串为奇数
    //若left!=right，则扩散的中点为 left和right，此时的回文子串为偶数
    public static int centerSpread(String s, int left, int right) {
        int len = s.length();
        int l = left;
        int r = right;
        while (l >= 0 && r <= len - 1) {
            //若相等则继续扩散
            if (s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            } else break;
        }//为什么还要减2  因为上面while循环终止了，此时s.charAt(l) != s.charAt(r)
        //所以此时的回文子串的左右边界其实是  l-1，  r-1
        return r - l + 1 - 2;
    }

    /**
     * 方法三
     * 动态规划解决回文数问题
     * 这个题目用到了动态规划的思想具体
     * 注意字符串的遍历顺序一定是从后向前的，
     * 因为这样才能解决之前没有计算而直接出答案的问题。
     * 这里的dp数组比较难想，是应该存储所经过的子字符串是否是回文数
     */
    public static int getLongestPalindrome3(String A, int n) {
        // write code here
        char[] aa = A.toCharArray();
        int max = 1;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int i = 1; i < n; i++)//i指向的是字符的最后一位
            for (int j = i - 1; j >= 0; j--) {//j指向的是字符的前部。
                if (i - j == 1) {//当两个指针靠近时，直接判断
                    dp[j][i] = (aa[i] == aa[j]);
                    if (max < i - j + 1)
                        max = i - j + 1;
                } else {
                    if (dp[j + 1][i - 1] && aa[i] == aa[j]) {
                        dp[j][i] = true;
                        if (max < i - j + 1)
                            max = i - j + 1;
                    } else
                        dp[j][i] = false;
                }
            }
        return max;
    }
}
