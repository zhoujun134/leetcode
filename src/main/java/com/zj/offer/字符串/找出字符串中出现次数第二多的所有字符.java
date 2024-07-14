package com.zj.offer.字符串;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @Author: zhoujun
 * @Date: 2021/4/1 10:35
 * @Description:
 */
public class 找出字符串中出现次数第二多的所有字符 {

    public static List<Character> foundChar(String str) {
        char[] chars = str.toCharArray();
        // 统计每个字符出现的次数
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i], map.get(chars[i]) + 1);
            } else {
                map.put(chars[i], 1);
            }
        }
        List<Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        // 根据 value 从大到小排序
        list.sort((t1, t2) -> t2.getValue().compareTo(t1.getValue()));
        return list.stream().limit(2)
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
//        String str = "aabbbccd";
          String str = "abbcasbbacbcaqq";
        foundChar(str).forEach(System.out::println);

    }
}
