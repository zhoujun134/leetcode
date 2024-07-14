package com.zj.offer.栈队列堆;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Author: Created by com.zj
 * @Date: 2021/08/22 上午 10:46
 * 作者：牛客782693764号
 * 链接：https://www.nowcoder.com/discuss/695260?type=all&order=time&pos=&page=0&ncTraceId=&channel=-1&source_id
 * =search_all_nctrack
 * 来源：牛客网
 * <p>
 * 1、mysql：查询2个任务（含）以上的需求
 * 题目描述：根据需求拆解可执行落地的实际任务
 * 以下为任务表
 * id（任务ID）  demand_id(需求ID）  name(任务名称）
 * 1                     1                                  xxx
 * 2                     1                                  xxx
 * 3                     1                                  xxx
 * 写一段SQL语句查出2个任务（含）以上的需求ID以及对应的任务数量，查询结果格式如下：
 * demand_id(需求ID）        count（数量）
 * 1                                         2
 * 3                                         5
 * 10                                       3
 * <p>
 * 输入：任务表
 * 输出：需求ID以及对应的任务数量
 * <p>
 * SELECT
 * demand_id,
 * count( 1 ) AS 次数
 * FROM
 * 任务表
 * GROUP BY
 * demand_id
 * HAVING
 * COUNT( 1 ) >=2
 * <p>
 * 2、求滑动窗口平均数的最大增幅
 * 一个自然数组arr，大小为k的滑动窗口从数组头部往数组尾部滑动，窗口每次滑动一位，窗口最后一位到达数组末尾时滑动结束。窗口每次滑动后，
 * 窗口内k个整数的平均值比滑动前会有一个变化幅度百分比p。
 * 输入描述:：输入数组喝窗口大小，数组和窗口大小用英文冒号分隔，数组内自然数用英文逗号分隔。
 * 输出描述：滑动开始到结束后出现的最大p值。
 * <p>
 * 3、湖泊抽水问题
 * 题目描述：
 * 你的国家有无数个湖泊，所有湖泊一开始都是空的。当第 n 个湖泊下雨的时候，如果第 n 个湖泊是空的，那么它就会装满水，否则这个湖泊会发生洪水。你的目标是避免任意一个湖泊发生洪水。
 * <p>
 * 给你一个整数数组 rains ，其中：
 * rains[i] > 0 表示第 i 天时，第 rains[i] 个湖泊会下雨。
 * rains[i] == 0 表示第 i 天没有湖泊会下雨，你可以选择 一个 湖泊并 抽干 这个湖泊的水。
 * 请返回一个数组 ans ，满足：
 * ans.length == rains.length
 * 如果 rains[i] > 0 ，那么ans[i] == -1 。
 * 如果 rains[i] == 0 ，ans[i] 是你第 i 天选择抽干的湖泊。
 * 如果有多种可行解，请返回它们中的 任意一个 。如果没办法阻止洪水，请返回一个 空的数组 。
 * 示例 1：
 * <p>
 * 输入：rains = [1,2,3,4]
 * 输出：[-1,-1,-1,-1]
 * 解释：第一天后，装满水的湖泊包括 [1]
 * 第二天后，装满水的湖泊包括 [1,2]
 * 第三天后，装满水的湖泊包括 [1,2,3]
 * 第四天后，装满水的湖泊包括 [1,2,3,4]
 * 没有哪一天你可以抽干任何湖泊的水，也没有湖泊会发生洪水。
 * 示例 2：
 * <p>
 * 输入：rains = [1,2,0,0,2,1]
 * 输出：[-1,-1,2,1,-1,-1]
 * 解释：第一天后，装满水的湖泊包括 [1]
 * 第二天后，装满水的湖泊包括 [1,2]
 * 第三天后，我们抽干湖泊 2 。所以剩下装满水的湖泊包括 [1]
 * 第四天后，我们抽干湖泊 1 。所以暂时没有装满水的湖泊了。
 * 第五天后，装满水的湖泊包括 [2]。
 * 第六天后，装满水的湖泊包括 [1,2]。
 * 可以看出，这个方案下不会有洪水发生。同时， [-1,-1,1,2,-1,-1] 也是另一个可行的没有洪水的方案。
 * <p>
 * 4、多线程按序打印
 */
public class 滑动窗口内平均数的最大增幅 {

    public static ArrayList<Double> getMaxP(int[] nums, int size) {
        ArrayList<Double> result = new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        if (nums.length < size) {
            return result;
        }
        for (int i = 0; i < size; i++) {
            maxHeap.add(nums[i]);
        }
        // 上一个滑动窗口的平均数
        double beforeMean = mean(maxHeap);
        result.add(0.0);
        for (int i = 0, j = i + size; j < nums.length; i++, j++) {
            // 维护堆内元素
            maxHeap.remove(nums[i]);
            maxHeap.add(nums[j]);
            // 求当前滑动窗口内的平均数
            double tempMean = mean(maxHeap);
            // 计算与上一个滑动窗口的比值
            result.add(beforeMean / tempMean);
            // 移动平均数
            beforeMean = tempMean;
        }
        return result;
    }

    private static double mean(PriorityQueue<Integer> maxHeap) {
        int sum = maxHeap.stream()
                .reduce(Integer::sum)
                .orElse(0);
        return (double) sum / maxHeap.size();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String inputString = input.nextLine();
        String[] split = inputString.split(":");
        String[] arrayStr = split[0].split(",");
        int size = Integer.parseInt(split[1]);
        int[] nums = new int[arrayStr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(arrayStr[i]);
        }
        getMaxP(nums, size).forEach(System.out::println);
    }
}
