package com.zj.offer.数组与矩阵;

/**
 * @Author: zhoujun
 * @Date: 2021/3/25 17:49
 * @Description: 给出两个有序的整数数组A和B，请将数组B合并到数组A中，变成一个有序的数组
 * 注意：
 * 可以假设A数组有足够的空间存放 数组的元素，A和B中初始的元素数目分别为 和
 */
public class 合并两个有序的数组 {

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 52, 0, 0, 0, 0, 0, 0};
        int[] B = {2, 3, 41, 62, 78, 81};
        merge2(A, 5, B, 6);
        for (int i = 0; i < A.length; i++) {
            System.out.println(A[i]);
        }
    }

    /**
     * 方法一
     * 采用快速排序的方式
     */
    public static void merge(int A[], int m, int B[], int n) {
        int index = 0;
        for (int i = m; i < A.length; i++) {
            if (index < n)
                A[i] = B[index++];
        }
        quickSqrt(A, 0, A.length - 1);
    }

    public static void merge2(int A[], int m, int B[], int n) {
        while (m > 0 && n > 0) {
            if (A[m - 1] >= B[n - 1]) {
                A[m + n - 1] = A[m - 1];
                m--;
            } else {
                A[m + n - 1] = B[n - 1];
                n--;
            }
        }
        if (n > 0) {
            for (int i = n; i >= 0; i--) {
                A[i] = B[n--];
            }
        }
    }

    public static void quickSqrt(int[] nums, int low, int high) {
        int i = low, j = high;
        if (i < j) {
            int temp = nums[low];
            while (i < j) {
                while (i < j && nums[j] >= temp) j--;
                if (i < j) {
                    nums[i++] = nums[j];
                }
                while (i < j && nums[i] < temp) i++;
                if (i < j) {
                    nums[j--] = nums[i];
                }
                nums[i] = temp;
                quickSqrt(nums, low, i - 1);
                quickSqrt(nums, i + 1, high);
            }
        }
    }
}
