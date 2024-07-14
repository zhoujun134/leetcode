package com.zj.offer.字符串;

import java.util.HashMap;

/**
 * @Author: zhoujun
 * @Date: 2021/3/29 9:40
 * @Description: 题目描述
 * 给定一个数组arr，返回arr的最长无的重复子串的长度(无重复指的是所有数字都不相同)。
 */
public class 找字符串的最长无重复子串 {

    public static void main(String[] args) {
        //        int[] arr = {2,2,3,4,3};
        int[] arr = {1, 2, 3, 4, 5, 1, 1, 1};
        System.out.println(maxLength(arr));


    }

    public static int maxLength(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int fast = 0;
        int slow = 0;
        int max = 0;
        while (fast < arr.length && slow < arr.length) {
            if (!map.containsKey(arr[fast])) {
                map.put(arr[fast], 1);
                fast++;
                max = Math.max(max, fast - slow);
            } else {
                map.clear();
                slow++;
            }
        }
        return max;

    }

    /**
     * 当某个数在之前出现过，这个时候就把子串的起点start往后推一个，但是有一种情况，
     * 比如1，2，3，4，3，5，1。到第二个3时，以后的子串起点start为4，
     * 到第二个1时，如果不取最大的start，按start = map.get(arr[end])+1
     * 算出起点start为2，显然以起点start=2，结尾end=1的子串234351有重复的，
     * 因此start要取最大的
     **/
    public static int maxLength2(int[] arr) {

        if (arr.length < 2) {
            return arr.length;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 1;
        for (int start = 0, end = 0; end < arr.length; end++) {
            if (map.containsKey(arr[end])) {
                start = Math.max(start, map.get(arr[end]) + 1);
            }
            res = Math.max(res, end - start + 1);
            map.put(arr[end], end);
        }
        return res;
    }
}
