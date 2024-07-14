package com.zj.offer.数字计算.二进制;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2023-03-15
 */
public class 计算一个数是由哪几个二的几次幂相加 {

    private static int getMaxofTwo(Integer number) {
        int i = 0;
        while (true) {
            double t = Math.pow(2, i);
            if (t > number) {
                return i - 1;
            } else if (t == number) {
                return i;
            }
            i++;
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // 得到输入
        int n = input.nextInt();
        int t, a;
        ArrayList<Integer> num = new ArrayList<>();
        while (true) {
            t = getMaxofTwo(n);
            num.add(t);
            a = (int) Math.pow(2, t);
            //找到最后一个指数
            if (a == n) {
                break;
            }
            n = n - a;
        }
        // 求出所有的指数
        // 格式化输出好看点
        for (int i = 0; i < num.size() - 1; i++) {
            System.out.print(2 + "^" + num.get(i) + "+");
        }

        System.out.println(2 + "^" + num.get(num.size() - 1));
    }
}
