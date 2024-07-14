package com.zj.offer.栈队列堆;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author junzhou
 * @date 2021/3/15 19:38
 * @description:TODO
 * @since 1.8
 */
public class 滑动窗口的最大值 {

    /**
     * 方法一, 使用大顶堆
     *
     * @param nums 数组
     * @param size 滑动窗口的大小
     * @return
     */
    public static ArrayList<Integer> maxInWindows(int[] nums, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        // 大顶堆, 顶部元素是最大
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                (o1, o2) -> o2 - o1);
        for (int i = 0; i < size; i++) {
            maxHeap.add(nums[i]);
        }
        // 取出堆的最大元素
        result.add(maxHeap.peek());
        for (int i = 0, j = i + size; j < nums.length; i++, j++) {
            maxHeap.remove(nums[i]);
            maxHeap.add(nums[j]);
            result.add(maxHeap.peek());
        }
        return result;
    }

    /**
     * 方法二 使用双端队列，存储当前的最大值
     *
     * @param nums 数组
     * @param k    滑动窗口的大小
     * @return
     */
    public static ArrayList<Integer> maxInWindows2(int[] nums, int k) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ArrayList<Integer> result = new ArrayList<>();
        if (nums.length == 0 || nums.length < k) {
            return result;
        }
        int begin = 0;
        for (int i = 0; i < nums.length; i++) {
            // 滑动窗口的起始位置
            begin = i - k + 1;
            if (deque.isEmpty()) {
                deque.add(i);
            }
            // deque.peekFirst() 获取队头元素
            else if (begin > deque.peekFirst()) {
                deque.pollFirst(); // 移除队头元素
            }
            // deque.peekLast() 取出队列尾部元素
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast(); //移除队尾元素
            }
            deque.add(i);
            if (begin >= 0 && !deque.isEmpty()) {
                result.add(nums[deque.peekFirst()]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        ArrayList<Integer> integers = maxInWindows2(nums, 4);
        integers.forEach(System.out::println);
    }
}
