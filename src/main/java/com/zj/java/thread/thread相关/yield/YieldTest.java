package com.zj.java.thread.thread相关.yield;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2023-01-18
 */
public class YieldTest {
    public static void main(String[] args) {
        new YieldTestImpl();
        new YieldTestImpl();
        new YieldTestImpl();
        Thread.interrupted();

    }
}

class YieldTestImpl implements Runnable {

    public YieldTestImpl() {
        // 创建并开启线程
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            if (i % 5 == 0) {
                // 当 i = 0 时，让出 CPU 执行权，放弃时间片，进行下一轮调度
                System.out.println(Thread.currentThread() + " yield CPU ...");
                // 当前线程放弃 CPU 执行权，放弃时间片，进行下一轮调度。
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread() + " is over ...");
    }
}
