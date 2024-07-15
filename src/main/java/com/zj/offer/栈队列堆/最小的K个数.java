package com.zj.offer.栈队列堆;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author junzhou
 * @date 2021/3/15 16:26
 * @description: 给定一个数组，找出其中最小的K个数。例如数组元素是  4,5,1,6,2,7,3,8这8个数字，
 * 则最小的4个数字是1,2,3,4。如果K>数组的长度，那么返回一个空的数组
 * @since 1.8
 */
public class 最小的K个数 {


    /**
     * 方法一
     * 大小为 K 的最小堆
     * 复杂度：O(NlogK) + O(K)
     * 特别适合处理海量数据
     * 维护一个大小为 K 的最小堆过程如下：使用大顶堆。在添加一个元素之后，如果大顶堆的大小大于 K，那么将大顶堆的堆顶元素去除，也就是将当前堆中值最大的元素去除，从而使得留在堆中的元素都比被去除的元素来得小。
     * <p>
     * 应该使用大顶堆来维护最小堆，而不能直接创建一个小顶堆并设置一个大小，企图让小顶堆中的元素都是最小元素。
     * <p>
     * Java 的 PriorityQueue 实现了堆的能力，PriorityQueue 默认是小顶堆，可以在在初始化时使用 Lambda 表达式 (o1, o2) -> o2 - o1
     * 来实现大顶堆。其它语言也有类似的堆数据结构。
     */
    public static ArrayList<Integer> getLeastNumbers2(int[] input, int k) {
        if (k > input.length || k <= 0) {
            return new ArrayList<>();
        }
        PriorityQueue<Integer> minHeap =
                new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for (int num : input) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return new ArrayList<>(minHeap);
    }

    /**
     * 方法二
     * 快速排序的方式解决
     */
    public static ArrayList<Integer> getLeastNumbers(int[] input, int k) {
        if (k > input.length || k <= 0) {
            return new ArrayList<>();
        }
        int[] tempArr = sortArray(input);
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            result.add(tempArr[i]);
        }
        return result;
    }

    public static int[] sortArray(int[] arr) {
        int[] arr2 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = arr[i];
        }
        quickSqrt(arr2, 0, arr2.length - 1);
        return arr2;
    }

    public static void quickSqrt(int[] arr, int low, int high) {
        int temp;
        int i = low, j = high;
        if (low < high) {
            temp = arr[low];
            while (i < j) {
                while (j > i && arr[j] >= temp) {
                    j--;
                }
                if (i < j) {
                    arr[i] = arr[j];
                    i++;
                }
                while (j > i && arr[i] < temp) {
                    i++;
                }
                if (i < j) {
                    arr[j] = arr[i];
                    j--;
                }
                arr[i] = temp;
                quickSqrt(arr, low, i - 1);
                quickSqrt(arr, i + 1, high);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 6, 2, 7, 3, 8};
        //        getLeastNumbers2(arr, 4)
        //                .forEach(System.out::println);

        getLeastNumbers(arr, 4)
                .forEach(System.out::println);


    }


}
