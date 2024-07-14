package com.zj.offer.栈队列堆;

import java.util.Stack;

/**
 * @author junzhou
 * @date 2021/3/15 16:14
 * @description: 实现一个包含 min() 函数的栈，该方法返回当前栈中最小的值。
 * @since 1.8
 */
public class 包含min函数的栈 {

    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node);
        minStack.push(minStack.isEmpty() ? node : Math.min(minStack.peek(), node));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }

}
