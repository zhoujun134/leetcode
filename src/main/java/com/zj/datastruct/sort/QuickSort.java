package com.zj.datastruct.sort;

import com.zj.datastruct.sort.util.PrintUtil;

/**
 * @Author: zhoujun
 * @Date: 2021/3/8 18:53
 * @Description: 快速排序
 */
public class QuickSort {

    public static void quickSort(int[] arr, int low, int high) {
        int temp;
        int i = low, j = high;
        if (low >= high) {
            return;
        }
        temp = arr[low];
        while (i < j) {
            while (j > i && arr[j] >= temp) --j;
            if (i < j) {
                arr[i] = arr[j];
                ++i;
            }
            while (i < j && arr[i] < temp) ++i;
            if (i < j) {
                arr[j] = arr[i];
                --j;
            }
            arr[i] = temp;
            quickSort(arr, low, i - 1);
            quickSort(arr, i + 1, high);
        }
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 2332, 21, 45, 67878, 12};
        System.out.println("排序之前：");
        PrintUtil.printIntArray(arr);

        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序之后：");
        PrintUtil.printIntArray(arr);
    }
}
