package com.zj.java.thread.线程;

/**
 * @Author: Created by com.zj
 * @Date: 2021/10/15 下午 04:44
 * 测试线程礼让
 * + 礼让线程，让当前正在执行的线程暂停，但不阻塞
 * + 将线程从运行状态转为就绪状态
 * + 让cpu重新调度，礼让不一定成功！看CPU心情
 */
public class TestYield implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 线程礼让开始运行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + " 线程礼让停止运行");
    }

    public static void main(String[] args) {
        TestYield yield = new TestYield();
        new Thread(yield, "a").start();
        new Thread(yield, "b").start();
    }
}