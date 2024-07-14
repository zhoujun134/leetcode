package com.zj.offer.栈队列堆;

import java.util.Stack;

/**
 * @author junzhou
 * @date 2021/3/15 15:54
 * @description:TODO
 * @since 1.8
 */
public class 两个栈实现队列 {


    Stack<Integer> in = new Stack<Integer>();
    Stack<Integer> out = new Stack<Integer>();

    /***
     * 入队
     * @param node
     */
    public void push(int node) {
        in.push(node);
    }

    public int pop() throws Exception {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        if (out.isEmpty()) {
            throw new Exception("queue is empty");
        }
        return out.pop();
    }

}
