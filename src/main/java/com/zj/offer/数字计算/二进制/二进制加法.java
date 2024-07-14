package com.zj.offer.数字计算.二进制;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2023-03-14
 */
public class 二进制加法 {

    private static String addBinary(String a, String b) {
        int aIndex = a.length() - 1;
        int bIndex = b.length() -1;
        StringBuilder result = new StringBuilder();
        int needAddOne = 0;
        while (aIndex >= 0 || bIndex >= 0) {
            int aDigit = aIndex >= 0 ? a.charAt(aIndex--) - '0' : 0;
            int bDigit = bIndex >= 0 ? b.charAt(bIndex--) - '0' : 0;
            int sum = aDigit + bDigit + needAddOne;
            needAddOne = sum >= 2 ? 1 : 0;
            result.append(sum >= 2 ? sum - 2 : sum);
        }
        if (needAddOne == 1) {
            result.append(1);
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "1111";
        String b = "1111";
        System.out.println(addBinary(a, b));
    }
}
