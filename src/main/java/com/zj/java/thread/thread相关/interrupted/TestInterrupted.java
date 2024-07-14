package com.zj.java.thread.thread相关.interrupted;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2023-01-18
 *
 */
public class TestInterrupted {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
        Thread threadOne = new Thread(() -> {
            // 中断标志为 true 时会退出循环，并且清除中断标志
            while (!Thread.interrupted()) {
                System.out.println("threadOne 还在处理逻辑，没有被中断");
            }
            System.out.println("threadOne isInterrupted: " + Thread.currentThread().isInterrupted());
        });

        // 启动线程
        threadOne.start();
        // 设置中断标志
        threadOne.interrupt();
        threadOne.join();
        System.out.println("main thread is over!");


    }

    /***
     * <p>interrupted 方法内部是获取当前线程的中断状态，这里虽然调用了 threadOne.interrupted() 方法，但是获取的是主线程的中断标志，因为主线程是当前线程，threadOne.interrupted() 和 Thread.interrupted() 方法的作用是一样的，目的都是获取当前线程的中断标志。</p>
     * @throws InterruptedException 打断异常
     */
    private static void test3() throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            for (;;){

            }
        });

        // 启动线程
        threadOne.start();
        // 设置中断标志
        threadOne.interrupt();
        // 获取中断标志
        System.out.println("threadOne isInterrupted: " + threadOne.isInterrupted());
        // 获取中断标志并重置
        System.out.println("threadOne interrupted: " + Thread.interrupted());
        // 获取中断标志并重置
        System.out.println("threadOne interrupted: " + Thread.interrupted());

        // 获取中断标志
        System.out.println("threadOne isInterrupted: " + threadOne.isInterrupted());

        threadOne.join();
        System.out.println("main thread is over!");
    }

    private static void test2() throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                System.out.println("threadOne 开始休眠 2000 秒");
                Thread.sleep(2000000);
                System.out.println("threadOne 唤醒了！");
            } catch (InterruptedException e) {
                System.out.println("threadOne 休眠挂起 2000 秒被 interrupted ");
                return;
            }
            System.out.println("threadOne is leaving normally!");
        });
        // 启动线程
        threadOne.start();

        // 确保子线程进入休眠状态
        Thread.sleep(1000);

        // 打断子线程的休眠，让子线程从 sleep 函数返回
        threadOne.interrupt();

        // 等待子线程执行完毕
        threadOne.join();
        System.out.println("main thread is over!");
    }


    private static void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            // 如采当前线寻呈被中断则退出循环
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread() + " hello");
            }
        });
        // 启动子线程
        thread.start();
        // 主线程休眠 1s，以便中断前让子程序输出
        Thread.sleep(1000);
        // 中断子线程
        System.out.println("main thread interrupt thread");
        thread.interrupt();
        // 等待子线程执行完毕
        thread.join();
        System.out.println("main is over ");
    }
}
