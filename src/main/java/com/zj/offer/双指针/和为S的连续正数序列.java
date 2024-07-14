package com.zj.offer.双指针;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: zhoujun
 * @Date: 2021/3/18 14:12
 * @Description: 输出所有和为 S 的连续正数序列。例如和为 100 的连续序列有：
 * [9, 10, 11, 12, 13, 14, 15, 16]
 * [18, 19, 20, 21, 22]。
 */
public class 和为S的连续正数序列 {
    public static void main(String[] args) {
        findContinuousSequence(100).forEach(list -> {
            System.out.println("============ 一组 ==============");
            list.forEach(i -> System.out.println(i + " "));
        });
    }


    public static List<List<Integer>> findContinuousSequence(int sum) {
        List<List<Integer>> ret = new ArrayList<>();
        int start = 1, end = 2;
        int curSum = 3;
        while (end < sum) {
            if (curSum > sum) {
                curSum -= start;
                start++;
            } else if (curSum < sum) {
                end++;
                curSum += end;
            } else {
                List<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                    list.add(i);
                }
                ret.add(list);
                curSum -= start;
                start++;
                end++;
                curSum += end;
            }
        }
        return ret;
    }
}
