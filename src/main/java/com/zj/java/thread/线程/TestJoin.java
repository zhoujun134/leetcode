package com.zj.java.thread.线程;

/**
 * @Author: Created by com.zj
 * @Date: 2021/10/15 下午 04:52
 * Join合并线程，待此线程执行完成后，再执行其他线程，其他线程阻塞
 * 可以想象成插队
 */
public class TestJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            System.out.println("线程 Vip 来了 " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 启动我们的线程
        TestJoin testJoin = new TestJoin();
        Thread myThread = new Thread(testJoin);
        myThread.start();

        // 主线程
        for (int i = 0; i < 500; i++) {
            if (i == 20){
                myThread.join();
            }
            System.out.println("main: " + i);
        }
    }
}
