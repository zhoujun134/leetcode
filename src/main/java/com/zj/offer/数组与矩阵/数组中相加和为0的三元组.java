package com.zj.offer.数组与矩阵;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: zhoujun
 * @Date: 2021/4/1 17:13
 * @Description: 给出一个有n个元素的数组S，S中是否有元素a,b,c满足a+b+c=0？找出数组S中所有满足条件的三元组。
 * 注意：
 * 三元组（a、b、c）中的元素必须按非降序排列。（即a≤b≤c）
 * 解集中不能包含重复的三元组。
 * 例如，给定的数组 S = {-10 0 10 20 -10 -40},解集为(-10, 0, 10) (-10, -10, 20)
 */
public class 数组中相加和为0的三元组 {
    public static void main(String[] args) {
        int[] num = {-2, 0, 1, 1, 2};
        System.out.println(threeSum(num));
    }

    public static List<List<Integer>> threeSum(int[] num) {

        List<List<Integer>> res = new ArrayList<>();
        if (num == null || num.length == 0) {
            return res;
        }
        if (num.length < 3) {
            return res;
        }
        Arrays.sort(num); //排序
        for (int i = 0; i < num.length - 1; i++) {
            int left = i + 1;
            int right = num.length - 1;
            while (right - left >= 1) {
                System.out.println(" left: " + left + "  right: " + right);
                int tempSum = num[left] + num[right] + num[i];
                if (tempSum == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(num[left]);
                    list.add(num[right]);
                    list.add(num[i]);
                    Collections.sort(list);
                    if (!res.contains(list)) {
                        res.add(list);
                    }
                    right--;
                } else if (tempSum < 0) {
                    left++;
                } else {
                    right--;
                }
            }

        }
        return res;
    }
}
