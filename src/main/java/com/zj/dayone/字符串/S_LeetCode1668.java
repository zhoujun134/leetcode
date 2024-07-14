package com.zj.dayone.字符串;

import java.util.Arrays;

public class S_LeetCode1668 {
    public static void main(String[] args) {

        String sequence = "abababcc";
        String word = "abc";
        int i = maxRepeating(sequence, word);
        System.out.println(i);
    }

    public static int maxRepeating(String sequence, String word) {
        int sequenceLength = sequence.length(), wordLength = word.length();
        if (sequenceLength < wordLength) {
            return 0;
        }

        int[] validArray = new int[sequenceLength];
        for (int i = wordLength - 1; i < sequenceLength; ++i) {
            boolean valid = true;
            for (int j = 0; j < wordLength; ++j) {
                if (sequence.charAt(i - wordLength + j + 1) != word.charAt(j)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                validArray[i] = (i == wordLength - 1 ? 0 : validArray[i - wordLength]) + 1;
            }
        }

        return Arrays.stream(validArray)
                .max()
                .getAsInt();
    }

    /**
     * 方法二
     * KMP 算法 + 动态规划
     * 方法一的数组 valid 本质上就是标记了字符串 word 在字符串 sequence 中所有出现的位置。
     *     而我们可以使用更高效的 KMP 算法 在 O(m+n) 的时间内得到数组 valid。
     *     对于 KMP 算法本身，本篇题解不再赘述，感兴趣的读者可以自行通过链接进行学习。
     *
     */
    public static int maxRepeating2(String sequence, String word) {
        int sequenceLength = sequence.length(), wordLength = word.length();
        if (sequenceLength < wordLength) {
            return 0;
        }
        int[] fail = new int[wordLength];
        Arrays.fill(fail, -1);
        for (int i = 1; i < wordLength; ++i) {
            int j = fail[i - 1];
            while (j != -1 && word.charAt(j + 1) != word.charAt(i)) {
                j = fail[j];
            }
            if (word.charAt(j + 1) == word.charAt(i)) {
                fail[i] = j + 1;
            }
        }

        int[] validArray = new int[sequenceLength];
        int j = -1;
        for (int i = 0; i < sequenceLength; ++i) {
            while (j != -1 && word.charAt(j + 1) != sequence.charAt(i)) {
                j = fail[j];
            }
            if (word.charAt(j + 1) == sequence.charAt(i)) {
                ++j;
                if (j == wordLength - 1) {
                    validArray[i] = (i >= wordLength ? validArray[i - wordLength] : 0) + 1;
                    j = fail[j];
                }
            }
        }

        return Arrays.stream(validArray)
                .max()
                .getAsInt();
    }
}
