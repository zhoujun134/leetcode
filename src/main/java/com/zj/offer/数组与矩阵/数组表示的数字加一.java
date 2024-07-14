package com.zj.offer.数组与矩阵;

import java.util.Arrays;

/**
 * @Author: zhoujun
 * @Date: 2021/3/30 15:39
 * @Description:
 */
public class 数组表示的数字加一 {
    public static void main(String[] args) {
        int[] nums = {9, 9, 9, 9};
        Arrays.stream(addOne(nums)).forEach(System.out::println);

    }

    public static int[] addOne(int[] nums) {
        int[] nums2 = new int[nums.length + 1];
        System.arraycopy(nums, 0, nums2, 1, nums.length);
        for (int i = nums2.length - 1; i >= 0; i--) {
            if (nums2[i] == 9) {
                nums2[i] = 0;
            } else {
                nums2[i] += 1;
                break;
            }
        }
        if (nums2[0] == 0) {
            System.arraycopy(nums2, 1, nums, 0, nums.length);
        } else {
            nums = nums2;
        }
        return nums;
    }
}
