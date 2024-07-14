package com.zj.java.thread.thread相关.join;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2023-01-18
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
//        test1();
        // 线程 one
        Thread threadOne = new Thread(() -> {
            System.out.println("threadOne begin run! ");
            for (; ; ) {
            }
        });
        // 获取主线程
        final Thread mainThread = Thread.currentThread();
        // 线程 two
        Thread threadTwo = new Thread(() -> {
            // 休眠 1s
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 中断主线程
            mainThread.interrupt();
        });
        // 启动子线程
        threadOne.start();
        // 延迟 1s 启动线程
        threadTwo.start();
        try {
            // 等待线程 one 执行结束
            threadOne.join();
        } catch (InterruptedException e) {
            System.out.println("main thread:" + e);
        }

    }

    private static void test1() throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("child threadOne over! ");
        });
        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("child threadTwo over! ");
        });
        // 启动子线程
        threadOne.start();
        threadTwo.start();
        System.out.println("wait all child thread over! ");
        // 等待子线程执行完毕，返回
        threadOne.join();
        threadTwo.join();
        System.out.println("all child thread over! ");
    }
}
