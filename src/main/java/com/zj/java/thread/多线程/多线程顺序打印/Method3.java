package com.zj.java.thread.多线程.多线程顺序打印;

/**
 * @Author: Created by com.zj
 * @Date: 2021/08/22 下午 01:06
 * 要求用三个线程，按顺序打印1,2,3,4,5.... 71,72,73,74, 75.
 * 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10, 然后是线程3打印11,12,13,14,15. 接着再由线程1打印16,17,18,19,20....以此类推, 直到线程3打印到75。
 */
public class Method3 {
    static int type = 1;//线程对应的类型，三个线程分开处理
    static int number = 1;//打印数字的起始

    public static void main(String[] args) {
        new Thread("第一个线程:") { // 创建一个线程，命名为第一个线程
            public void run() {
                try {
                    synchronized (Method3.class) {
                        while (number <= 75) {
                            if (type == 1) {//当type的值为1的时候，由第一个线程来打印
                                for (int i = 0; i < 5; i++) {
                                    System.out.println(Thread.currentThread().getName() + ":" + number++);
                                }
                                type = 2;//修改类型值
                                Method3.class.notifyAll();//唤醒其他进入休眠的线程
                            } else {
                                Method3.class.wait();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();

        new Thread("第二个线程:") { // 创建二个线程，命名为第二个线程
            public void run() {
                try {
                    synchronized (Method3.class) {
                        while (number <= 75) {
                            if (type == 2) {
                                for (int i = 0; i < 5; i++) {
                                    System.out.println(Thread.currentThread().getName() + ":" + number++);
                                }
                                type = 3;
                                Method3.class.notifyAll();
                            } else {
                                Method3.class.wait();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
        new Thread("第三个线程:") { // 创建三个线程，命名为第三个线程
            public void run() {
                try {
                    synchronized (Method3.class) {
                        while (number <= 75) {
                            if (type == 3) {
                                for (int i = 0; i < 5; i++) {
                                    System.out.println(Thread.currentThread().getName() + ":" + number++);
                                }
                                type = 1;
                                Method3.class.notifyAll();
                            } else {
                                Method3.class.wait();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }
}
