package com.zj.offer.字符串;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String T = scanner.nextLine();
        int length = Integer.parseInt(T);
        int index = 0;
        String[] res = new String[length];

        for (int i = 0; i < Integer.parseInt(T); i++) {
            String n = scanner.nextLine();
            String numStr = scanner.nextLine();
            String[] numStrs = numStr.split(" ");
            int[] temp = new int[numStrs.length];
            for (int j = 0; j < numStrs.length; j++) {
                temp[j] = Integer.parseInt(numStrs[j]);
            }
            String result = solution(temp);
            res[index++] = result;
        }
        for (int i = 0; i < length; i++) {
            System.out.println(res[i]);
        }
    }

    private static String solution(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                return "No";
            }
        }
        return "Yes";
    }

    private static void quickSort(int[] arr, int low, int high) {

        if (low < high) {
            int index = getIndex(arr, low, high);
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }

    }

    private static int getIndex(int[] arr, int low, int high) {
        int tmp = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= tmp) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= tmp) {
                low++;
            }
            arr[high] = arr[low];

        }
        arr[low] = tmp;
        return low;
    }


}