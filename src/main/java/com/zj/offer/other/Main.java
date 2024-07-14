package com.zj.offer.other;

/**
 * @Author: Created by com.zj
 * @Date: 2021/08/26 上午 11:36
 */
public class Main {
    public static void main(String[] args) {

        int[][] adjMatrix = {
                {0, 6, 3, -1, -1, -1},
                {6, 0, 2, 5, -1, -1},
                {3, 2, 0, 3, 4, -1},
                {-1, 5, 3, 0, 2, 3},
                {-1, -1, 4, 2, 0, 5},
                {-1, -1, -1, 3, 5, 0}};
        int[] result = djsktara(adjMatrix);
        System.out.println("顶点0到图中所有顶点之间的最短距离为：");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }


    public static int[] djsktara(int[][] adjMatrix) {
        // 用于存放顶点0到其它顶点的最短距离
        int[] result = new int[adjMatrix.length];
        // 用于判断顶点是否被遍历
        boolean[] used = new boolean[adjMatrix.length];
        // 表示顶点0已被遍历
        used[0] = true;
        for (int i = 1; i < adjMatrix.length; i++) {
            result[i] = adjMatrix[0][i];
            used[i] = false;
        }

        for (int i = 1; i < adjMatrix.length; i++) {
            // 用于暂时存放顶点0到i的最短距离，初始化为Integer型最大值
            int min = Integer.MAX_VALUE;
            int k = 0;
            // 找到顶点0到其它顶点中距离最小的一个顶点
            for (int j = 1; j < adjMatrix.length; j++) {
                if (!used[j] && result[j] != -1 && min > result[j]) {
                    min = result[j];
                    k = j;
                }
            }
            // 将距离最小的顶点，记为已遍历
            used[k] = true;
            // 然后，将顶点0到其它顶点的距离与加入中间顶点k之后的距离进行比较，更新最短距离
            for (int j = 1; j < adjMatrix.length; j++) {
                // 当顶点j未被遍历时
                // 首先，顶点k到顶点j要能通行；这时，当顶点0到顶点j的距离大于顶点0到k再到j的距离
                // 或者顶点0无法直接到达顶点j时，更新顶点0到顶点j的最短距离
                if (!used[j] && adjMatrix[k][j] != -1 && (result[j] > min + adjMatrix[k][j] || result[j] == -1)) {
                    result[j] = min + adjMatrix[k][j];
                }
            }
        }
        return result;
    }
}