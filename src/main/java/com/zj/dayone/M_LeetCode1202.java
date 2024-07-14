package com.zj.dayone;

import java.util.List;

/**
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 *
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 *
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 * 示例 2：
 *
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 * 示例 3：
 *
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-string-with-swaps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M_LeetCode1202 {

    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        String tempS = "";

        for (int i = 0; i < pairs.size(); i++) {
            
        }

        return null;
    }

    /**
     * 交换字符串中的两个字符
     * @param s 待交换的字符串
     * @param start 交换的开始位置
     * @param end 交换的 结束位置
     * @return 交换后的 新 字符串
     */
    public static String exchange(String s, int start, int end){
        char[] chars = s.toCharArray();
        char startC = chars[start];
        chars[start] = chars[end];
        chars[end] = startC;
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println("abcs".compareTo("abcz"));
        System.out.println("adbc".compareTo("abcd"));
        System.out.println("adbc".compareTo("adbc"));
    }
}
