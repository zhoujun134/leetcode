package com.zj.dayone.栈;

import java.util.Stack;

/**
 * Gaal 解析器
 * https://leetcode.cn/problems/goal-parser-interpretation/solution/she-ji-goal-jie-xi-qi-by-leetcode-soluti-npnp/
 */
public class S_LeetCode1678 {

    public static void main(String[] args) {
        String command = "(al)G(al)()()G";
        String result = interpret(command);
        System.out.println(result);
    }

    public static String interpret(String command) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            char currentChar = command.charAt(i);
            if (currentChar == 'G') {
                result.append("G");
                continue;
            }
            if (currentChar == ')') {
                dealStackString(stack, result);
                continue;
            }
            stack.push(currentChar);
        }
        return result.toString();
    }

    /**
     * 处理栈中的数据
     * @param stack 临时存储栈的数据
     * @param result 最终的结果
     */
    private static void dealStackString(Stack<Character> stack, StringBuilder result) {
        StringBuilder temp = new StringBuilder();
        while (!stack.isEmpty()) {
            Character pop = stack.pop();
            temp.append(pop);
            if (pop == '('){
                break;
            }
        }
        String tempStr = temp.toString();
        if (tempStr.equals("(")) {
            result.append("o");
        }
        if (tempStr.equals("la(")){
            result.append("al");
        }
    }

    /**
     * 方法二
     *
     * @param command 需要解析的字符串
     * @return Goal解析器的结果
     */
    public static String interpret2(String command) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'G') {
                res.append("G");
            } else if (command.charAt(i) == '(') {
                if (command.charAt(i + 1) == ')') {
                    res.append("o");
                } else {
                    res.append("al");
                }
            }
        }
        return res.toString();
    }

}
