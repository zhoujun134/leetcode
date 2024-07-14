package com.zj.datastruct.sort.test;

import com.zj.datastruct.sort.util.Sort;

/**
 * @author zhoujun07
 * Created on 2021-07-01
 */
public class Selection<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] arr) {
        T t;
        int min = 0;
        int i, j;

        for (i = 0; i < arr.length; i++) {
            t = arr[i];
            for (j = i + 1; j < arr.length; j++) {
                if (less(arr[j], t)) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    public static void main(String[] args) {
        Selection<Integer> selection = new Selection<>();

        Integer[] arr = {1, 23, 3, 4, 56, 777, 12};

        selection.sort(arr);

        System.out.println("============ 打印整型数组 start ==================");
        for (Integer integer : arr) {
            System.out.print(" " + integer);
        }
        System.out.println();
        System.out.println("============= 打印整型数组 end ===================");

    }
}
