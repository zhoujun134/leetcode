package com.zj.java.thread.thread相关.notifyWait;

/**
 * @author zhoujun09@kuaishou.com
 * Created on 2023-01-17
 * <p>当前线程调用共享变量的 wait（）方法后只会释放当前共享变量上的锁，
 * 如果当前线程还持有其他共享变量的锁，则这些锁是不会被释放的。</p>
 */
public class TestWait {
    // 创建资源
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread threadA = new Thread(() -> {
            try {
                // 获取 resourceA 共享资源的监视器锁
                synchronized (resourceA) {
                    System.out.println("threadA get resourceA lock");
                    // 获取 resourceB 共享资源的监视器锁
                    synchronized (resourceB) {
                        System.out.println("threadA get resourceB lock");
                        // 线程A阻塞，并释放获取到的 resourceA 的锁
                        System.out.println("threadA release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 创建线程
        Thread threadB = new Thread(() -> {
            try {
                //休眠 1s
                Thread.sleep(1000);
                // 获取 resourceA 共享资源的监视器锁
                synchronized (resourceA) {
                    System.out.println("threadB get resourceA lock");
                    System.out.println("threadB try get resourceB lock...");
                    // 获取 resourceB 共享资源的监视器锁
                    synchronized (resourceB) {
                        System.out.println("threadB get resourceB lock");
                        // 线程B阻塞，并释放获取到的 resourceA 的锁
                        System.out.println("threadB release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 启动线程
        threadA.start();
        threadB.start();
        // 等待两个线程结束
        threadA.join();
        threadB.join();
        System.out.println("main over");
    }
}
