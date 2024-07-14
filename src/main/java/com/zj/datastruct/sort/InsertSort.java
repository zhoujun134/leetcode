package com.zj.datastruct.sort;

import com.zj.datastruct.sort.util.PrintUtil;

/**
 * 插入排序：
 * 1. 直接插入排序
 * 2. 选择插入排序
 * 3. 冒泡排序
 */
public class InsertSort {


    /**
     * 直接插入排序
     * 依次选出当前位置，选择插入到前边有序数组中
     * 依次从后边无序的数组中选取待插入的数。
     *
     * @param arr 待排序的数组
     */
    public void insertSort(int[] arr) {
        int temp, j;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            j = i - 1;
            while (j >= 0 && temp <= arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    /**
     * 选择插入排序
     * 依次从剩下的数组中选取最小数组插入当前有序序列的后边。
     *
     * @param arr 待排序的数组
     */
    public void selectSqrt(int[] arr) {
        int i, j, k;
        int temp;
        for (i = 1; i < arr.length; i++) {
            k = i;
            for (j = i + 1; j < arr.length; j++) {
                if (arr[k] > arr[j]) {
                    k = j;
                }
            }
            temp = arr[k];
            arr[k] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * 冒泡排序
     * 依次将无序序列的值和前面的数进行比较，
     * 如左边大于右边的数，两个交换，
     * 依次遍历，当没有交换时结束遍历
     *
     * @param arr 待排序数组
     */
    public void bubleSqrt(int[] arr) {
        int i, j;
        int temp, flage;
        for (i = arr.length - 1; i >= 0; i--) {
            flage = 0;
            for (j = 1; j <= i; j++) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    flage = 1;
                }
            }
            if (flage == 0) break;

        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 45, 51, 5, 65, 667, 7, 12};
        InsertSort insertSort = new InsertSort();
        System.out.println("数组初始化为：");
        PrintUtil.printIntArray(arr);
        insertSort.bubleSqrt(arr);
        System.out.println("利用插入排序排序之后的数组为：");
        PrintUtil.printIntArray(arr);
    }
}
