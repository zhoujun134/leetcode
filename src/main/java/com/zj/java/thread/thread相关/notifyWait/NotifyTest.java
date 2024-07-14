package com.zj.java.thread.thread相关.notifyWait;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2023-01-17
 */
public class NotifyTest {
    // 创建资源
    private static final Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread threadA = new Thread(() -> {
            // 获取 resourceA 共享资源的监视器锁
            synchronized (resourceA) {
                System.out.println("threadA get resourceA lock");
                try {
                    System.out.println("threadA begin wait");
                    resourceA.wait();
                    System.out.println("threadA end wait");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        // 创建线程
        Thread threadB = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("threadB get resourceA lock");
                try {
                    System.out.println("threadB begin wait");
                    resourceA.wait();
                    System.out.println("threadB end wait");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        // 创建线程
        Thread threadC = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("threadC begin notify");
                resourceA.notify();
            }
        });
        // 启动线程
        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();

        // 等待线程结束
        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("休眠 2 秒！");
        Thread.sleep(2000);
        synchronized (resourceA) {
            System.out.println("threadC begin notify");
            resourceA.notifyAll();
        }
        System.out.println("main over");
    }

}
