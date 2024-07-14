package com.zj.offer.数字计算.二进制;

import java.util.Arrays;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2023-03-15
 * 输入一个非负数 n，请计算 0 到 n 之间每个数字的二进制形式中 1 的个数，
 * 并输出一个数组。例如，输入的 n 为 4，由于 0、1、2、3、4 的二进制形式中 1 的个数分别为 0、1、1、2、1，
 * 因此输出数组 [0，1，1，2，1]。
 */
public class 前n个数字二进制形式中1的个数 {

    private static int[] compute(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int j = i;
            while (j != 0) {
                result[i]++;
                j = j & (j - 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] computeResult = compute(4);
        Arrays.stream(computeResult)
                .forEach(System.out::println);
    }
}
