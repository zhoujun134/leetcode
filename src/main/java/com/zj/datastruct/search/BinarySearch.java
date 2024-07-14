package com.zj.datastruct.search;

import com.zj.datastruct.sort.util.PrintUtil;

/**
 * @Author: zhoujun
 * @Date: 2021/3/9 7:09
 * @Description: 二分查找
 */
public class BinarySearch {

    public static int binarySearch(int[] arr, int des) {
        int middle = -1;
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            middle = (start + end) / 2;
            if (des == arr[middle]) {
                return middle;
            } else if (des <= arr[middle]) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return middle;
    }

    public static void main(String[] args) {

//        int[] arr= {1,2,2332,21,45,67878,12};
        int[] arr = {1, 2, 2, 12, 21, 45, 2332, 67878};

        System.out.println("排序之前：");
        PrintUtil.printIntArray(arr);
        System.out.println("位置: >> " + BinarySearch.binarySearch(arr, 2));
//        System.out.println("排序之后：");
//        PrintUtil.printIntArray(arr);
    }
}
