package com.zj.java.thread.多线程.多线程顺序打印;

/**
 * @Author: Created by com.zj
 * @Date: 2021/08/22 下午 01:02
 * 使用三个标志来标识哪个线程应该等待，分别在三个线程中使用同步代码块。
 */
public class Method2 {
    private static boolean flag1 = true;
    private static boolean flag2 = false;
    private static boolean flag3 = false;

    public static void main(String[] args) {
        final Method2 o = new Method2();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                while (true) {
                    synchronized (o) {
                        if (!flag1) {
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName());
                            flag1 = false;
                            flag2 = true;
                            flag3 = false;
                            o.notifyAll();
                            break;
                        }
                    }
                }
            }
        }, "A");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                while (true) {
                    synchronized (o) {
                        if (!flag2) {
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName());
                            flag1 = false;
                            flag2 = false;
                            flag3 = true;
                            o.notifyAll();
                            break;
                        }
                    }
                }
            }
        }, "B");

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                while (true) {
                    synchronized (o) {
                        if (!flag3) {
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName());
                            flag1 = true;
                            flag2 = false;
                            flag3 = false;
                            o.notifyAll();
                            break;
                        }
                    }
                }
            }
        }, "C");

        t1.start();
        t2.start();
        t3.start();
    }
}
