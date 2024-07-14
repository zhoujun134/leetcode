package com.zj.dayone;

import com.zj.datastruct.sort.util.PrintUtil;

/**
 * @Author: zhoujun
 * @Date: 2021/2/11 17:19
 * @Description: 703. 数据流中的第 K 大元素
 */
public class S_leetCode703 {


    static class KthLargest {

        public int[] nums;
        private int k;

        public KthLargest(int k, int[] nums) {

            this.k = k;
            this.nums = nums;
            /**
             * 排序
             */
            sort(this.nums);

        }

        public int add(int val) {
            boolean flag = false;
            int[] result = new int[this.nums.length + 1];
            for (int i = 0; i < this.nums.length; i++) {
                if (this.nums[i] > val && !flag) {
                    for (int j = i; j < this.nums.length; j++) {
                        result[j + 1] = this.nums[j];
                    }
                    result[i] = val;
                    flag = true;
                    break;
                }
                result[i] = this.nums[i];
            }

            if (!flag) {
                result[this.nums.length] = val;
            }
            this.nums = result;

            PrintUtil.printIntArray(this.nums);
            return this.nums[this.k - 1];

        }


        /**
         * 排序
         *
         * @param nums 待排序的数组
         */
        public void sort(int[] nums) {
            int i, j, k;

            int temp;
            for (i = 0; i < nums.length; i++) {
                k = i;
                for (j = i + 1; j < nums.length; j++) {
                    if (nums[k] > nums[j]) {
                        k = j;
                    }
                }
                temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
            }

        }

        public static void main(String[] args) {
            int[] nums = {4, 5, 8, 2};

            System.out.println("排序之前");
            PrintUtil.printIntArray(nums);
            System.out.println("排序之后");
            KthLargest kthLargest = new KthLargest(3, nums);
//        kthLargest.sort(nums);
            PrintUtil.printIntArray(kthLargest.nums);
            System.out.println(kthLargest.add(3));

            System.out.println(kthLargest.add(5));
            System.out.println(kthLargest.add(10));
            System.out.println(kthLargest.add(9));
            System.out.println(kthLargest.add(4));


        }
    }
}