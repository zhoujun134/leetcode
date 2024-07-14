package com.zj.offer.栈队列堆;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author junzhou
 * @date 2021/3/15 17:09
 * @description: 数据流中的中位数
 * @since 1.8
 */
public class 数据流中的中位数 {

    /**
     * 快速排序的方式
     */
    public static int GetMedian(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 排序
        quickSqrt(nums, 0, nums.length - 1);
        if (nums.length % 2 == 0) {
            return (nums[nums.length / 2] + nums[nums.length / 2 + 1]) / 2;
        } else {
            return nums[nums.length / 2];
        }
    }

    public static void quickSqrt(int[] nums, int low, int high) {
        int temp;
        int i = low, j = high;
        if (low < high) {
            temp = nums[low];
            while (i < j) {
                while (i < j && nums[j] >= temp) {
                    j--;
                }
                if (i < j) {
                    nums[i] = nums[j];
                    i++;
                }
                while (i < j && nums[i] < temp) {
                    i++;
                }
                if (i < j) {
                    nums[j] = nums[i];
                    j--;
                }
                nums[i] = temp;
                quickSqrt(nums, low, i - 1);
                quickSqrt(nums, i + 1, high);
            }
        }

    }

    /* 大顶堆，存储左半边元素 */
    private static PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    /* 小顶堆，存储右半边元素，并且右半边元素都大于左半边 */
    private static PriorityQueue<Integer> right = new PriorityQueue<>();
    /* 当前数据流读入的元素个数 */
    private static int N = 0;

    public static void insert(Integer val) {
        /* 插入要保证两个堆存于平衡状态 */
        if (N % 2 == 0) {
            /* N 为偶数的情况下插入到右半边。
             * 因为右半边元素都要大于左半边，但是新插入的元素不一定比左半边元素来的大，
             * 因此需要先将元素插入左半边，然后利用左半边为大顶堆的特点，取出堆顶元素即为最大元素，此时插入右半边 */
            left.add(val);
            right.add(left.poll());
        } else {
            right.add(val);
            left.add(right.poll());
        }
        N++;
    }

    /**
     * 方法二 使用大顶堆和小顶堆实现
     */
    public static Double getMedian2() {
        if (N % 2 == 0 && left.peek() != null && right.peek() != null) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return (double) (right.peek() == null ? 0 : right.peek());
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 4};
        Arrays.stream(nums)
                .forEach(数据流中的中位数::insert);
        System.out.println(getMedian2());

    }
}
