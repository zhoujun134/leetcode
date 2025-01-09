package com.zj.offer.字符串.回文串;

/**
 * @Author: Created by com.zj
 * @Date: 2021/08/26 下午 09:27
 */
public class 最多删除一个字符使其成为回文串 {

    public static void main(String[] args) {

        System.out.println(isPalindromeByDeleteOneCharater("aan"));

    }

    // 最多删除一个字符，使其变成回文串
    public static boolean isPalindromeByDeleteOneCharater(String str) {
        if (str.length() < 3) {
            return true;
        }
        char[] ch = str.toCharArray();
        int i = 0, j = ch.length - 1;

        while (i < j) {
            if (ch[i] != ch[j]) {
                return isPalindrome(ch, i + 1, j) || isPalindrome(ch, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }

    public static boolean isPalindrome(char[] ch, int i, int j) {
        while (i < j) {
            if (ch[i] != ch[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
