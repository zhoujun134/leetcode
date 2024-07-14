package com.zj.offer.字符串;

/**
 * @Author: zhoujun
 * @Date: 2021/3/26 18:20
 * @Description:
 */
public class 找字符串的最长子串 {
    public static void main(String[] args) {
        System.out.println(maxSubString("abcababaa"));
    }

    public static int maxSubString(String str) {
        char[] nums = new char[128];
        int fast = 0;
        int slow = 0;
        int max = 0;
        while (fast < str.length() && slow < str.length()) {
            if (nums[str.charAt(fast)] == 0) {
                nums[str.charAt(fast)] = 1;
                fast++;
                max = Math.max(max, fast - slow);
            } else {
                nums[str.charAt(slow)] = 0;
                slow++;
            }
        }
        return max;
    }


}
