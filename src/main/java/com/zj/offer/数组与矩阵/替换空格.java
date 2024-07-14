package com.zj.offer.数组与矩阵;

/**
 * @author junzhou
 * @date 2021/3/14 16:45
 * @description: 将一个字符串中的空格替换成 "%20"。
 * Input:
 * "A B"
 * <p>
 * Output:
 * "A%20B"
 * @since 1.8
 */
public class 替换空格 {

    public static String replaceSpace(StringBuffer str) {
        int P1 = str.length() - 1;
        //        int spaceLength = 0;
        // 计算空格的数量
        for (int i = 0; i <= P1; i++) {
            if (str.charAt(i) == ' ') {
                str.append("  ");
            }
        }
        int P2 = str.length() - 1;
        while (P1 < P2 && P1 >= 0) {
            char c = str.charAt(P1--);
            if (c == ' ') {
                str.setCharAt(P2--, '0');
                str.setCharAt(P2--, '2');
                str.setCharAt(P2--, '%');
            } else {
                str.setCharAt(P2--, c);
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {

        System.out.println(replaceSpace(new StringBuffer("A B C")));

    }
}
