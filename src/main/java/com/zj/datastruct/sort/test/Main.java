package com.zj.datastruct.sort.test;

/**
 * @ClassName Main
 * @Author zj
 * @Description
 * @Date 2024/5/13 09:05
 * @Version v1.0
 **/
public class Main {
    public static void main(String[] args) {

    }

    public static void quickSort(int low, int high, int[] arr) {
        if (low >= high) {
            return;
        }
        int i = low, j = high;
        int temp = arr[low];
        while (i < j) {
            while (i < j && arr[j] > temp) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
            }
            while (i < j && arr[i] <= temp) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
            }
            arr[i] = temp;
            quickSort(low, i - 1, arr);
            quickSort(i + 1, high, arr);
        }

    }
}
