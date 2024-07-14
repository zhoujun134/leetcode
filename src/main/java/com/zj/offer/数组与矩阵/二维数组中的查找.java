package com.zj.offer.数组与矩阵;

/**
 * @author junzhou
 * @date 2021/3/14 16:23
 * @description:
 * 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。
 * 给定一个数，判断这个数是否在该二维数组中。
 * @since 1.8
 */
public class 二维数组中的查找 {

    public static boolean find(int target, int[][] matrix){
        // 如果二维数组为空
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int i = 0;
        // 列值
        int j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0){
            if (matrix[i][j] > target){
                j--;
            }else if (matrix[i][j] < target){
                i++;
            }else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[][] matrix = {{1,2,3,4,5},
//                {6,7,8,9,10}};

        int[][] matrix = {
            {1,   4,  7, 11, 15},
            {2,   5,  8, 12, 19},
            {3,   6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}};
//        System.out.println(matrix[0].length);
//        System.out.println(matrix.length);
        System.out.println(find(10, matrix));


    }

}
