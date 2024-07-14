package com.zj.dayone;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目地址
 * https://leetcode.cn/problems/fruit-into-baskets/
 */
public class M_LeetCode904 {

    /**
     * 解题思路
     * 1. 使用 left 和 right 分别表示满足要求的窗口的左右边界.
     * 2. 哈希表存储这个窗口内的数以及出现的次数.
     * 3. 每次将 right 移动一个位置, 并将 fruits[right] 加入哈希表.
     *      如果 此时哈希表不满足条件(哈希表中出现超过两个键值对),
     *          那么我们需要不断移动 left, 并将 fruit[left] 从哈希表中移除, 直到哈希表满足要求为止.
     *      需要注意:
     *          将 fruits[left] 从哈希表中移除后，如果 fruits[left] 在哈希表中的出现次数减少为 0，需要将对应的键值对从哈希表中移除。
     *
     *
     * 题目分析
     *      时间复杂度: O(n) 其中 n 是数组 fruit 的长度
     *      空间复杂度: O(1) 哈希表中,最多会存在三个键值对,可以看成使用了常数级别的空间.
     * @param fruits 第 i 棵树上水果的种类
     * @return 你可以收集的水果的最大数目
     */
    public static int totalFruit2(int[] fruits) {
        int n = fruits.length;
        // 哈希表 left 和 right 窗口内的数以及出现的次数.
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();

        int left = 0, ans = 0;
        for (int right = 0; right < n; ++right) {
            // 加入最右边的数, 和默认的次数 1
            cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
            // 调整 哈希表
            while (cnt.size() > 2) {
                // 将左边的数移除
                cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
                if (cnt.get(fruits[left]) == 0) {
                    cnt.remove(fruits[left]);
                }
                ++left;
            }
            // 计算最大值
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] fruits = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        int totalFruit = totalFruit2(fruits);
        System.out.println(totalFruit);
    }
}
