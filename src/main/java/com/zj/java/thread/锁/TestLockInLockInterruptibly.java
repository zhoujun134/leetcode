//package com.zj.java.thread.锁;
//
//import com.lmax.disruptor.RingBuffer;
//import com.lmax.disruptor.dsl.Disruptor;
//import com.lmax.disruptor.util.DaemonThreadFactory;
//import org.junit.Test;
//import sun.misc.Contended;
//
//import java.nio.ByteBuffer;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//import static java.lang.Thread.sleep;
//
///**
// * @Author: Created by com.zj
// * @Date: 2021/08/28 上午 11:55
// */
//public class TestLockInLockInterruptibly {
//
//    /**
//     * lock()忽视interrupt(), 拿不到锁就 一直阻塞：
//     */
//    @Test
//    public void test1() throws Exception {
//        final Lock lock = new ReentrantLock();
//        lock.lock();
//        sleep(1000);
//        Thread t1 = new Thread(() -> {
//            lock.lock();
//            System.out.println(Thread.currentThread().getName() + " interrupted.");
//        });
//        t1.start();
//        sleep(1000);
//        // 试图将t1中断执行，但并不能
//        t1.interrupt();
//        sleep(5000);
//    }
//
//    /**
//     * lockInterruptibly() 会响应打扰 并catch到InterruptedException
//     */
//    @Test
//    public void test2() throws Exception {
//        final Lock lock = new ReentrantLock();
//        lock.lock();
//        sleep(1000);
//        Thread t1 = new Thread(() -> {
//            try {
//                lock.lockInterruptibly();
//            } catch (InterruptedException e) {
//                System.out.println(Thread.currentThread().getName() + " interrupted.");
//            }
//        });
//        t1.start();
//        sleep(1000);
//        // 试图将t1中断执行，是可以的，产生了一个InterruptedException异常
//        t1.interrupt();
//        sleep(5000);
//        System.out.println("主线程执行完毕");
//    }
//
//    /**
//     * 以下实验验证：当线程已经被打扰了（isInterrupted()返回true）。
//     * 则线程使用lock.lockInterruptibly()，直接会被要求处理InterruptedException。
//     */
//    @Test
//    public void test3() throws Exception {
//        final Lock lock = new ReentrantLock();
//        Thread t1 = new Thread(() -> {
//            try {
//                System.out.println("start: " + System.currentTimeMillis());
//                sleep(2000);
//                lock.lockInterruptibly();
//            } catch (InterruptedException e) {
//                System.out.println("InterruptedException: " + e.getMessage() + "  end: " + System.currentTimeMillis());
//                System.out.println(Thread.currentThread().getName() + " interrupted.");
//            }
//        });
//        t1.start();
//        sleep(1000);
//        System.out.println("开始 interrupted: " + System.currentTimeMillis());
//        t1.interrupt();
//        sleep(10000);
//    }
//
//    @Test
//    public void test4() {
//
//        /* 指定 RingBuffer 大小, 9 // 必须是 2 的 N 次方 */
//        int bufferSize = 1024;
//
//        /* 构建 Disruptor */
//        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new,
//                bufferSize, DaemonThreadFactory.INSTANCE);
//
//        /* 注册事件处理器 */
//        disruptor.handleEventsWith(
//                (event, sequence, endOfBatch) ->
//                        System.out.println("E: " + event));
//        /* 启动 Disruptor */
//        disruptor.start();
//        /* 获取 RingBuffer */
//        RingBuffer<LongEvent> ringBuffer
//                = disruptor.getRingBuffer();
//        /* 生产 Event */
//        ByteBuffer bb = ByteBuffer.allocate(8);
//        for (long l = 0; true; l++) {
//            bb.putLong(0, l);
//            /* 生产者生产消息 */
//            ringBuffer.publishEvent(
//                    (event, sequence, buffer) ->
//                            event.set(buffer.getLong(0)), bb);
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 注意，需要在运行参数上添加 -XX:-RestrictContended 才会生效。
//     */
//    @Contended
//    private volatile long a;
//    @Contended
//    volatile long b;
//    @Contended
//    volatile long c;
//    @Contended
//    volatile long d;
//
//    @Test
//    public void test5() throws InterruptedException {
//        TestLockInLockInterruptibly mqTest = new TestLockInLockInterruptibly();
//        Thread thread1 = new Thread(() -> {
//            for (int i = 0; i < 10000_0000L; i++) {
//                mqTest.a = i;
//            }
//        });
//
//        Thread thread2 = new Thread(() -> {
//            for (int i = 0; i < 10000_0000L; i++) {
//                mqTest.b = i;
//            }
//        });
//        Thread thread3 = new Thread(() -> {
//            for (int i = 0; i < 10000_0000L; i++) {
//                mqTest.c = i;
//            }
//        });
//        Thread thread4 = new Thread(() -> {
//            for (int i = 0; i < 10000_0000L; i++) {
//                mqTest.d = i;
//            }
//        });
//        long start = System.currentTimeMillis();
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread1.join();
//        thread2.join();
//        thread3.join();
//        thread4.join();
//        System.out.println(System.currentTimeMillis() - start);
//    }
//
//}
//
///* 自定义 Event */
//class LongEvent {
//    private long value;
//
//    public void set(long value) {
//        this.value = value;
//    }
//
//    @Override
//    public String toString() {
//        return "LongEvent{" +
//                "value=" + value +
//                '}';
//    }
//}
