package com.zj.datastruct;

import java.util.Stack;

/**
 * @author junzhou
 * @date 2021/1/14 21:35
 * @description:TODO
 * @since 1.8
 *
 * [] 和 () 两种括号的匹配
 */
public class 括号匹配 {

    public static boolean fatch(String s){
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        stack.push(chars[0]);
        boolean flage = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(' || chars[i] == '['){
                stack.push(chars[i]);
                continue;
            }
            char pop = stack.pop();
            if (chars[i] == ')'){
                if (pop == '(') {
                    continue;
                }
                flage = false;
            }
            if (chars[i] == ']'){
                if (pop == '['){
                    continue;
                }
                flage = false;
            }
            if (!flage) break;
        }
        return flage;
    }

    public static void main(String[] args) {
        String s = "(()]";
        System.out.println(fatch(s));
    }
}
