package com.zj.offer.双指针;


/**
 * @Author: zhoujun
 * @Date: 2021/3/18 14:28
 * @Description: Input:
 * "I am a student."
 * <p>
 * Output:
 * "student. a am I"
 * <p>
 * 思路：先旋转每个单词，再旋转整个字符串。
 */
public class 反转单词顺序列 {

    public static void main(String[] args) {
        System.out.println(reverseSentence("Ia Ab Student."));
    }

    public static String reverseSentence(String str) {
        char[] arr = str.toCharArray();
        int n = str.length();
        int i = 0, j = 0;
        while (j <= n) {
            // 一个单词一个单词的反转
            if (j == n || arr[j] == ' ') {
                reverse(arr, i, j - 1);
                i = j + 1;
                System.out.println(">>> "+ new String(arr));
            }
            j++;
        }
        System.out.println("没有反转之前：" + new String(arr));
        // 反转整体的字符
        reverse(arr, 0, n - 1);
        return new String(arr);
    }


    /**
     * 反转字符
     */
    public static void reverse(char[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i++, j--);
        }
    }

    /**
     * 交换数组中的两个字符
     */
    public static void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
