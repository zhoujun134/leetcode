package com.zj.dayone;

/**
 * @author junzhou
 * @date 2020/12/29 22:28
 * @description:
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/patching-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 1.8
 */
public class D_LeetCode330 {
    public static int minPatches(int[] nums, int n) {
        int patches = 0;
        long x = 1;
        int length = nums.length, index = 0;
        while (x <= n) {
            if (index < length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }

    public static void main(String[] args) {
//        int[] nums = {1,2,2};
//        int n = 5;
        int[] nums = {1,5,10};
        int n = 20;
        System.out.println(minPatches(nums, n));
    }
}
