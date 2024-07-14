package com.zj.offer.双指针;

/**
 * @Author: zhoujun
 * @Date: 2021/3/18 14:49
 * @Description: Input:
 * S="abcXYZdef"
 * K=3
 * <p>
 * Output:
 * "XYZdefabc"
 * 解题思路
 * 先将 "abc" 和 "XYZdef" 分别翻转，得到 "cbafedZYX"，然后再把整个字符串翻转得到 "XYZdefabc"。
 */
public class 左旋转字符串 {

    public static void main(String[] args) {
        System.out.println(leftRotateString("ABCDEFGHI", 2));
    }

    public static String leftRotateString(String str, int n) {
        if (n == 0 || str.length() == 0) {
            return str;
        }
        int len = str.length();
        char[] arr = str.toCharArray();
        reverse(arr, 0, n - 1);
        System.out.println("convert 1:  " + new String(arr));
        reverse(arr, n, len - 1);
        System.out.println("convert 2:  " + new String(arr));
        reverse(arr, 0, len - 1);
        System.out.println("convert 3:  " + new String(arr));
        return new String(arr);
    }

    public static void reverse(char[] c, int i, int j) {
        while (i < j) {
            swap(c, i++, j--);
        }
    }

    public static void swap(char[] c, int i, int j) {
        char t = c[i];
        c[i] = c[j];
        c[j] = t;
    }
}
