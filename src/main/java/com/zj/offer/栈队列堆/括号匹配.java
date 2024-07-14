package com.zj.offer.栈队列堆;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Author: zhoujun
 * @Date: 2021/3/18 21:36
 * @Description:
 */
public class 括号匹配 {


    public static void main(String[] args) {

        System.out.println(fetch("]"));


    }

    public static HashMap<Character, Character> kuo = new HashMap<Character, Character>() {{
        this.put(']', '[');
        this.put('}', '{');
        this.put(')', '(');
    }};

    public static boolean fetch(String str) {
        char[] arr = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            // 如果是左括号就进栈
            if (kuo.containsValue(arr[i])) {
                stack.push(arr[i]);
            }// 若果是右括号就出栈
            else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character c = stack.pop();
                if (c != kuo.get(arr[i])) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    public static boolean isValid(String s) {
        // write code here
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char t = s.charAt(i);
            if (map.containsValue(t)) {
                stack.push(t);
            }
            if (map.containsKey(t)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char t2 = stack.pop();
                if (t2 != map.get(t)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
