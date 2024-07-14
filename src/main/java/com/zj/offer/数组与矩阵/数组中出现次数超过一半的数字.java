package com.zj.offer.数组与矩阵;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhoujun
 * @Date: 2021/4/1 10:55
 * @Description:
 */
public class 数组中出现次数超过一半的数字 {
    public static int MoreThanHalfNum_Solution(int [] array) {
        if (array.length == 1)
            return array[0];
        int ban = array.length / 2 ;
        Map<Integer, Integer> map = new HashMap<>();
        for (int j : array) {
            if (map.containsKey(j)) {
                map.put(j, map.get(j) + 1);
                if (map.get(j) > ban) {
                    return j;
                }
            } else {
                map.put(j, 1);
            }
        }
        System.out.println(map);
        return 0;
    }

    public static void main(String[] args) {
//        int[] arrays = {1,2,3,2,2,2,5,4,2};
        int[] arrays = {1,2,3,2,2,2,5,4,2};
        System.out.println(MoreThanHalfNum_Solution(arrays));
    }
}
