package com.zj.offer.数组与矩阵;

import com.zj.datastruct.sort.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author junzhou
 * @date 2021/3/17 20:39
 * @description: 给你一个 无序且重复的数组
 * 输出与目标值的对应的索引 (i, j)
 * @since 1.8
 */
public class 求无序且重复数组中目标值的两个索引 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 2, 3, -1, 2, 5, 1};
        System.out.println("排序-----");

        PrintUtil.printIntArray(nums);
        test3(nums, 3)
                .forEach(lis -> {
                    System.out.println("----start----");
                    lis.forEach(System.out::println);
                    System.out.println("----end----");

                });


//        test(nums, 5).forEach(System.out::println);
    }

    public static List<List<Integer>> test(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    int finalI = i;
                    int finalJ = j;
                    result.add(new ArrayList<Integer>() {{
                        this.add(finalI);
                        this.add(finalJ);
                    }});
                }
            }
        }
        return result;
    }

    /**
     * 方法二，快排 + 二分查找
     * @param nums
     * @param target
     */
    public static List<List<Integer>> test2(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        // 排序
        quickSort(nums, 0, nums.length - 1);

        int p;
        for (int i = 0; i < nums.length - 1; i++) {
            p = search(nums, i + 1, nums.length - 1, target - nums[i]);
            if (p > 0) {
                List<Integer> integers = Arrays.asList(nums[i], nums[p]);
                if (result.contains(integers)) {
                    continue;
                }
                result.add(integers);
            }
        }
        return result;
    }

    public static int search(int[] nums, int low, int high, int target) {
        int mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return Integer.MIN_VALUE;
    }

    public static void quickSort(int[] nums, int low, int high) {
        int i = low;
        int j = high;
        if (i < j) {
            int temp = nums[i];
            while (i < j) {
                while (i < j && nums[j] >= temp) {
                    j--;
                }
                if (i < j) {
                    nums[i++] = nums[j];
                }
                while (i < j && nums[i] < temp) {
                    i++;
                }
                if (i < j) {
                    nums[j--] = nums[i];
                }
                nums[i] = temp;
                quickSort(nums, low, i - 1);
                quickSort(nums, i + 1, high);
            }
        }
    }

    /**
     * 使用 hashMap
     * @param nums
     * @param target
     */
    public static List<List<Integer>> test3(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)){
                List<Integer> integers = Arrays.asList(nums[i], diff);
                if (result.contains(integers)){
                    continue;
                }
                result.add(integers);
            }
            map.put(nums[i], i);
        }
        return result;
    }
}
