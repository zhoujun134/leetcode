package com.zj.offer.other;

import test.util.GsonUtils;

import java.util.*;

public class 表达式求值 {
    public static void main(String[] args) {
//        String s1 = "3+2*3*4-1";
        String str = "5+2*3+(7+4)";

        System.out.println(computePostfixExpression2(str));
//        String str = "5+2*3/4+(6/2+3)";
        String postfixExpression = getPostfixExpression(str);
        System.out.println(postfixExpression);
        Double result = computePostfixExpression(postfixExpression);
        System.out.println("result: " + result);
    }


    public static String computePostfixExpression2(String postfixExpression) {
        char[] charArray = postfixExpression.toCharArray();
        List<String> opts = Arrays.asList("+", "-", "*", "/", "(", ")");
        Stack<String> operation = new Stack<>();
        StringBuilder result = new StringBuilder();
        // 1+ (( 2+3) * 4) -5
        for (char curChar : charArray) {
            String curCharString = String.valueOf(curChar);
            if (opts.contains(curCharString)) {
                if ("(".equals(curCharString)) {
                    operation.push(curCharString);
                } else if (")".equals(curCharString)) {
                    while (!operation.isEmpty() && !operation.peek().equals("(")) {
                        result.append(operation.pop());
                    }
                    operation.pop();
                } else {
                    // 加减剩除
                    if ("*".equals(curCharString) || "/".equals(curCharString)) {
                        if (!operation.isEmpty()
                                && (operation.peek().equals("/")
                                || operation.peek().equals("*"))) {
                            result.append(operation.pop());
                        }
                        operation.push(curCharString);
                    } else if ("+".equals(curCharString) || "-".equals(curCharString)) {
                        if (!operation.isEmpty()
                                && (operation.peek().equals("/")
                                || operation.peek().equals("*")
                                || operation.peek().equals("+")
                                || operation.peek().equals("-"))) {
                            result.append(operation.pop());
                        }
                        operation.push(curCharString);
                    }
                }
            } else {
                result.append(curCharString);
            }
        }
        while (!operation.isEmpty()) {
            result.append(operation.pop());
        }

        return result.toString();
    }


    /**
     * 顺序扫描表达式的每一项，然后根据他的类型做如下相应操作：
     * 若该项是操作数，则将其压入栈中，
     * 若该项是操作符<op>，则连续从栈中退出两个操作数Y和X， 形成运算指令 X<op>Y,并将计算结果压入栈中。
     * 当表达式的所有项都扫描并处理万后，栈顶存放的就是最后计算的结果。
     */
    public static Double computePostfixExpression(String postfixExpression) {
        Deque<Double> numbers = new LinkedList<>();
        List<String> opList = Arrays.asList("+", "-", "*", "/");
        String[] strArray = postfixExpression.split(",");
        for (int i = 0; i < strArray.length; i++) {
            String str = strArray[i];
            if (opList.contains(str)) {
                Double b = numbers.pop();
                Double a = numbers.pop();
                numbers.push(computeAAndBByOps(a, b, str));
                continue;
            }
            numbers.push(Double.parseDouble(str));
        }
        return numbers.pop();
    }

    private static Double computeAAndBByOps(Double a, Double b, String ops) {
        if (ops.equals("+")) {
            return a + b;
        } else if (ops.equals("-")) {
            return a - b;
        } else if (ops.equals("*")) {
            return a * b;
        } else if (ops.equals("/")) {
            return a / b;
        } else {
            return -1.0;
        }
    }

    /**
     * 转换为后缀表达式，使用逗号把每个部分隔离，保证100等超过2位数的数字出现问题。
     * 将中缀表达式转换为后缀表达式的算法思想如下：
     * 从左向右开始扫描中缀表达式，
     * 遇到数字时，加入后缀表达式，
     * 遇到运算符时：
     * a. 若为'(', 入栈。
     * b. 若为')'，则以次把栈中的运算符加入后缀表达式，直到出现'(', 从占中删除'('。
     * c. 若为除括号以外的其他运算符，当其优先级高于除'('外的栈顶运算符时，直接入栈，否则从栈顶开始，以次弹出比当前处理的运算符
     * 优先级高或者相等的运算符，直到一个比它优先级低的或者遇到'('为止。
     */
    public static String getPostfixExpression(String str) {
        Deque<Character> stack = new LinkedList<>();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '+' || c == '-') {
                buffer.append(",");
                if (!stack.isEmpty() &&
                        (stack.peek() == '-'
                                || stack.peek() == '*'
                                || stack.peek() == '+'
                                || stack.peek() == '/')) {
                    buffer.append(stack.pop());
                    buffer.append(",");
                }
                stack.push(c);
            } else if (c == '*' || c == '/') {
                buffer.append(",");
                if (!stack.isEmpty()
                        && (stack.peek() == '*'
                        || stack.peek() == '/')) {
                    buffer.append(stack.pop());
                    buffer.append(",");
                }
                stack.push(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    buffer.append(",");
                    buffer.append(stack.pop());
                }
                stack.pop();
            } else {
                buffer.append(c);
            }
        }
        while (!stack.isEmpty()) {
            buffer.append(",");
            buffer.append(stack.pop());
        }
        return buffer.toString();
    }

}
