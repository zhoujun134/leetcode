package com.zj.java.thread.thread相关.sleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepTest2 {
    // 创建一个独占锁
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // 创建线程A
        Thread threadA = new Thread(() -> {
            // 获取独占锁
            lock.lock();
            try {
                System.out.println("child threadA is in sleep");
                Thread.sleep(10000);
                System.out.println("child threadA is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        });
        // 创建线程B
        Thread threadB = new Thread(() -> {
            // 获取独占锁
            lock.lock();
            try {
                System.out.println("child threadB is in sleep");
                Thread.sleep(10000);
                System.out.println("child threadB is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        });
        // 启动线程
        threadA.start();
        threadB.start();
    }
}