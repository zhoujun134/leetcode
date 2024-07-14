package com.zj.offer.栈队列堆;

/**
 * @author zhoujun07 <zhoujun07@kuaishou.com>
 * Created on 2021-09-03
 */
public class CustomStack {

    /**
     * 存放数据
     */
    private int[] stackData;

    /**
     * 存放当前栈 stackData 中的最大值
     */
    private int[] currentMaxStack;

    /**
     * 当前数据栈 stackData 指针
     */
    private int top = -1;

    /**
     * 当前最大数据栈 currentMaxStack 指针
     */
    private int currentMaxTop = -1;

    public CustomStack(int capacity) {
        stackData = new int[capacity];
        currentMaxStack = new int[capacity];
    }

    public void push(int data) {
        if (this.top >= stackData.length) {
            throw new RuntimeException("栈已满,");
        }
        stackData[++top] = data;
        // 初始化
        if (currentMaxTop == -1) {
            currentMaxStack[++currentMaxTop] = data;
            return;
        }
        // 维护最大的值
        if (currentMaxStack[currentMaxTop] <= data) {
            currentMaxStack[++currentMaxTop] = data;
        }

    }

    public int pop() {
        if (top < 0) {
            throw new RuntimeException("当前栈为空,");
        }
        int currentData = stackData[top];
        stackData[top--] = 0;
        if (currentMaxStack[currentMaxTop] == currentData) {
            currentMaxStack[currentMaxTop--] = 0;
        }
        return currentData;
    }

    /**
     * 获取当前栈中的最大值
     */
    public int getNowStackMaxData() {
        return currentMaxStack[currentMaxTop];
    }

    public static void main(String[] args) {

    }
}
