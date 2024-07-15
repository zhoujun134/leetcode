package com.zj.datastruct.sort.util;

/**
 * 打印函数
 */
public class PrintUtil {

    /**
     * 打印整型数组
     * @param arr 需要打印的整型数组
     */
    public static void printIntArray(int[] arr){
        System.out.println("============ 打印整型数组 start ==================");
        for (int j : arr) {
            System.out.print(" " + j);
        }
        System.out.println();
        System.out.println("============= 打印整型数组 end ===================");
    }
}
