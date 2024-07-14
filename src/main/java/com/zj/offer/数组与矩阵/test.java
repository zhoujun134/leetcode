package com.zj.offer.数组与矩阵;

import java.util.HashMap;

/**
 * @author zhoujun07 <zhoujun07@kuaishou.com>
 * Created on 2021-07-07
 */
public class test {
    public static void main(String[] args) {
        System.out.println(findOneChar("abasss"));
    }


    /**
     * 替换空格
     */
    public static void replaceSpace(StringBuffer str) {
        int P1 = str.length() - 1;
        for (int i = 0; i < P1; i++) {
            if (str.charAt(i) == ' ') {
                str.append("  ");
            }
        }
        int P2 = str.length() - 1;
        while (P1 > 0 && P2 > P1) {
            if (str.charAt(P1) == ' ') {
                str.setCharAt(P2--, '0');
                str.setCharAt(P2--, '2');
                str.setCharAt(P2--, '%');
                P1--;
            }
            str.setCharAt(P2--, str.charAt(P1--));
        }
    }

    /**
     * 按顺时针的方向，从外到里打印矩阵的值。下图的矩阵打印结果为：
     * 1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10
     * Example:
     *         int[][] matrix = {
     *                 {1, 2, 3, 4},
     *                 {5, 6, 7, 8},
     *                 {9, 10, 11, 12}
     *         };
     *         printArrayByClockwise(matrix);
     */
    public static void printArrayByClockwise(int[][] matrix) {
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;

        while (r1 <= r2 && c1 <= c2) {
            // 上
            for (int i = c1; i <= c2; i++) {
                System.out.print(matrix[r1][i] + " ");
            }
            // 右
            for (int i = r1 + 1; i <= r2; i++) {
                System.out.print(matrix[i][c2] + " ");
            }
            if (r1 != r2)
            {
                // 下
                for (int i = c2 - 1; i >= c1; i--) {
                    System.out.print(matrix[r2][i] + " ");
                }
            }
            if (c1 != c2)
            {
                // 左
                for (int i = r2 - 1; i > r1; i--) {
                    System.out.print(matrix[i][c1] + " ");
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
    }

    public static Character findOneChar(String str){
        HashMap<Character, Integer> hashMap = new HashMap<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++){
            char c = chars[i];
            if (hashMap.containsKey(c)){
                hashMap.put(c, hashMap.get(c) + 1);
            }else {
                hashMap.put(c, 1);
            }
        }
        for (int i = 0; i < chars.length; i++){
            if (hashMap.get(chars[i]) == 1){
                return chars[i];
            }
        }
        return ' ';
//        // 方法二
//        int[] cnts = new int[128];
//        for (int i = 0; i < str.length(); i++)
//            cnts[str.charAt(i)]++;
//        for (int i = 0; i < str.length(); i++)
//            if (cnts[str.charAt(i)] == 1)
//                return i;
//        return -1;
//
    }
}
