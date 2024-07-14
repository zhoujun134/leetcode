package com.zj.datastruct.sort;

import com.zj.datastruct.sort.util.PrintUtil;

/**
 * @Author: zhoujun
 * @Date: 2021/2/10 12:48
 * @Description: 希尔排序
 */
public class ShellSqrt {

    public void shellSqrt(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                temp = arr[i];
                int j;
                for (j = i; j >= gap && temp < arr[j - gap]; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        ShellSqrt shellSqrt = new ShellSqrt();

        int[] arr = {1, 2, 2332, 21, 45, 67878, 12};

        System.out.println("排序之前：");
        PrintUtil.printIntArray(arr);

        shellSqrt.shellSqrt(arr);
        System.out.println("排序之后：");
        PrintUtil.printIntArray(arr);
    }


    public static void shellSqrt2(int[] arr) {
        int N = arr.length;
        int gap = N / 2;
        while (gap >= 1) {

            for (int i = gap; i < N; i++) {
                int t = i;
                for (int j = i; j >= gap; j -= gap) {
                    if (arr[j] <= arr[j - gap]) {
                        t = j;
                    }
                }
                swap(arr, i, t);
            }

            gap = gap / 2;

        }


    }

    private static void swap(int[] arr, int i, int t) {
        int temp = arr[t];
        arr[t] = arr[i];
        arr[i] = temp;
    }
}
