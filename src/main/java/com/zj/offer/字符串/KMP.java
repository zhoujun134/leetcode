package com.zj.offer.字符串;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2023-03-08
 */
public class KMP {

    public static void main(String[] args) {
        String a = "ABCABCABCDEF";
        String b = "ABCABCD";
        /**
         * A B C A B C D
         * 0 0 0 1 2 3 0
         */
        System.out.println(kmpSearch(a, b));

    }

    private static int[] buildNext(String subString) {
        int[] nextResult = new int[subString.length()];
        // next 数组初始元素为 0
        nextResult[0] = 0;
        // next 数组指针
        int nextIndex = 1;
        // 当前公共后最的长度
        int prefixLen = 0;
        int i = 1;
        while (i < subString.length()) {
            if (subString.charAt(prefixLen) == subString.charAt(i)) {
                prefixLen += 1;
                nextResult[nextIndex++] = prefixLen;
                i += 1;
            } else {
                if (prefixLen == 0) {
                    nextResult[nextIndex++] = 0;
                    i += 1;
                } else {
                    prefixLen = nextResult[prefixLen - 1];
                }
            }
        }
        return nextResult;
    }

    private static int kmpSearch(String originString, String subString) {
        // 构建子串
        int[] next = buildNext(subString);
        // 主串指针
        int mainIndex = 0;
        // 子串指针
        int subIndex = 0;
        while (mainIndex < originString.length()) {
            if (originString.charAt(mainIndex) == subString.charAt(subIndex)) {
                mainIndex += 1;
                subIndex += 1;
            } else if (subIndex > 1) {
                // 字符匹配失败, 根据 next 跳过子串的前几个字符的匹配、
                subIndex = next[subIndex - 1];
            } else {
                // 子串的第一个字符就匹配失败
                mainIndex += 1;
            }
            // 匹配成功
            if (subIndex == subString.length()) {
                return mainIndex - subIndex;
            }
        }
        return 0;
    }
}
