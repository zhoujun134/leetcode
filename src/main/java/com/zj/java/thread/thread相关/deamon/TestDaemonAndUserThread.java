package com.zj.java.thread.thread相关.deamon;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2023-01-18
 * <p> daemon 线程和 user 线程</p>
 */
public class TestDaemonAndUserThread {
    public static void main(String[] args) {
        test1();
//        test2();
    }

    private static void test1() {
        Thread daemonThread = new Thread(() -> {
        });
        // 设置为守护线程
        daemonThread.setDaemon(true);
        // 启动线程
        daemonThread.start();
    }

    private static void test2() {
        Thread daemonThread = new Thread(() -> {
            for(;;);
        });
        // 设置为守护线程
        daemonThread.setDaemon(true);
        // 启动线程
        daemonThread.start();
    }

}
