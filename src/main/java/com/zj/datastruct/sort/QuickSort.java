package com.zj.datastruct.sort;
/**
 * @Author: zhoujun
 * @Date: 2021/3/8 18:53
 * @Description: 快速排序
 */
public class QuickSort {
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int temp = arr[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && arr[j] >= temp) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                ++i;
            }
            while (i < j && arr[i] <= temp) {
                i++;
            }
            if (i <= j) {
                arr[j] = arr[i];
                j--;
            }
            arr[i] = temp;
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }

    public static void main(String[] args) {

        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        System.out.println("排序之前：");
        printIntArray(arr);

        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序之后：");
        printIntArray(arr);
    }

    public static void printIntArray(int[] arr){
        System.out.println("============ 打印整型数组 start ==================");
        for (int j : arr) {
            System.out.print(" " + j);
        }
        System.out.println();
        System.out.println("============= 打印整型数组 end ===================");
    }
}
