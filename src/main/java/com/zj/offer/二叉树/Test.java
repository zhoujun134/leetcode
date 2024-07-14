package com.zj.offer.二叉树;

/**
 * @Author: zhoujun
 * @Date: 2021/3/20 14:24
 * @Description:
 */
public class Test {

    public static  int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) {

        int [] nums = {7, 6, 5, 1, 3};

        System.out.println(f1(nums));

    }

    public static int f1(int [] arr){
        int len = arr.length;
        if (len <= 1) {
            return 0;
        }
        return process1(arr, -1, 0, len-1);
    }

    public static int process1(int[] arr, int cur, int L, int R){

        int length = R - L + 1;
        if (length == 0 ){
            return 0;
        }
        int ans = MAX;

        for (int i=L; i<= R; i++){
            int temp = 0;
            if (cur >= 0){
                temp = arr[cur] * arr[i];
            }
            ans = Math.min(temp + process1(arr, i, L, i-1) + process1(arr, i, i+1, R),
                    ans);
        }
        return ans;
    }
}
