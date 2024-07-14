package com.zj.dayone.暴力破解;

import com.zj.datastruct.sort.util.PrintUtil;

/**
 * <a href="https://leetcode.cn/problems/coordinate-with-maximum-network-quality/">题目地址</a>
 */
public class M_LeetCode1620 {
    public static void main(String[] args) {
//        int[][] towers = {
//                {1, 2, 5},
//                {2, 1, 7},
//                {3, 1, 5}};

        int[][] towers = {{23,11,21}};
        int[] result = bestCoordinate(towers, 2);

        PrintUtil.printIntArray(result);
    }


    public static int[] bestCoordinate(int[][] towers, int radius) {
        int xmin = 0, xmax = 0, ymin = 0, ymax = 0;
        for (int[] t : towers) {
            xmin = Math.min(xmin, t[0]);
            xmax = Math.max(xmax, t[0]);
            ymin = Math.min(ymin, t[1]);
            ymax = Math.max(ymax, t[1]);
        }

        int r2 = radius * radius;
        int strongest = Integer.MIN_VALUE, strongestX = Integer.MIN_VALUE, strongestY = Integer.MIN_VALUE;
        for (int x = xmin - radius; x <= xmax + radius; x++) {
            if (x < 0) {
                continue;
            }
            for (int y = ymin - radius; y <= ymax + radius; y++) {
                if (y < 0) {
                    continue;
                }
                int quality = 0;
                for (int[] t : towers) {
                    int dis2 = (t[0] - x) * (t[0] - x) + (t[1] - y) * (t[1] - y);
                    if (dis2 <= r2) {
                        quality += Math.floor(t[2] / (1 + Math.sqrt(dis2)));
                    }
                }
                //System.out.printf("%s, %s, %s, %s\n", x, y, quality, strongest);
                if (quality > strongest) {
                    strongest = quality;
                    strongestX = x;
                    strongestY = y;
                }
                // 因为我们在迭代的时候，x和y都是递增的，所以 quality = strongest 的情况，已经被自动处理好了，不用担心字典序的问题。
            }
        }
        return new int[]{strongestX, strongestY};
    }
}
