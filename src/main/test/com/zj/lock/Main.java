package com.zj.lock;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Main
 * @Author zj
 * @Description
 * @Date 2024/7/22 19:14
 * @Version v1.0
 **/
public class Main {
    final static NonReentrantLock lock = new NonReentrantLock();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();
    final static Queue<String> queue = new LinkedBlockingQueue<String>();
    final static int queueSize = 10;

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            //获取独占锁
            lock.lock();
            try {
                //(1)如果队列满了，则等待
                while (queue.size() == queueSize) {
                    notEmpty.await();
                }
                //（2）添加元素到队列
                queue.add("ele");
                //（3）唤醒消费线程
                notFull.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //释放锁
                lock.unlock();
            }
        });
        Thread consumer = new Thread(() -> {
            //获取独占锁
            lock.lock();
            try {
                //队列空，则等待
                while (0 == queue.size()) {
                    notFull.await();
                }
                //消费一个元素
                String ele = queue.poll();
                //唤醒生产线程
                notEmpty.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //释放锁
                lock.unlock();
            }
        });
        //启动线程
        producer.start();
        consumer.start();
    }
}

