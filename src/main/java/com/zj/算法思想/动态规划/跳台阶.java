package com.zj.算法思想.动态规划;

/**
 * @Author: zhoujun
 * @Date: 2021/4/7 20:08
 * @Description:
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class 跳台阶 {
    public static int jumpFloor(int target) {
        if (target == 1) return 1;
        if (target == 2) return 2;
        return jumpFloor(target - 1) + jumpFloor(target - 2);
    }


    public static void main(String[] args) {
        System.out.println(jumpFloor(12));
        System.out.println(jumpFloor2(12));
    }
    public static int jumpFloor2(int target){
        int a = 1, b = 1;
        for (int i = 2; i <= target; i++) {
            a = a + b;
            b = a - b;
        }

        return a;
    }
}
