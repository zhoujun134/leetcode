package com.zj.dayone.字符串;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhoujun09
 * Created on 2022-10-26
 * 无重复的最长子串
 * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">题目地址</a>
 */
public class M_LeetCode3 {

    public static int lengthOfLongestSubstring(String s) {

        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;

    }


    public static int lengthOfLongestSubstring2(String s) {
        int length = s.length();
        int curMaxLength = 0;
        Set<Character> characterSet = new HashSet<>();
        for (int i = 0; i < length; i++) {
            characterSet.clear();
            char curChar = s.charAt(i);
            characterSet.add(curChar);
            int k = i + 1;
            while (k < length && !characterSet.contains(s.charAt(k))) {
                characterSet.add(s.charAt(k));
                k++;
            }
            int tempMaxLength = k - i;
            if (curMaxLength < tempMaxLength) {
                curMaxLength = tempMaxLength;
            }
        }
        return curMaxLength;

    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abcabcabc"));
    }
}
