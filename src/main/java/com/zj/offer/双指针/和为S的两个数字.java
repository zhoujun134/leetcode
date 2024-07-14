package com.zj.offer.双指针;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhoujun
 * @Date: 2021/3/18 14:04
 * @Description: 在有序数组中找出两个数，使得和为给定的数 S。如果有多对数字的和等于 S，输出两个数的乘积最小的。
 */
public class 和为S的两个数字 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 5, 6};
        //        test(arr, 5).forEach(System.out::println);
        int[] ints = twoSum(arr, 5);
        System.out.println(ints[0] + "   " + ints[1]);
    }


    /**
     * @param arr 数组
     * @param S 和
     */
    public static ArrayList<Integer> test(int[] arr, int S) {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 0, j = arr.length - 1, sum = 0;
        while (i < j) {
            if (arr[i] + arr[j] == S) {
                list.add(i);
                list.add(j);
                return list;
            }
            if (arr[i] + arr[j] > S) {
                j--;
            } else {
                i++;
            }
        }
        return list;
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        //没有结果返回null，不写报错
        return null;
    }
}
