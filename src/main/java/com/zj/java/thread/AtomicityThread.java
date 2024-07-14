package com.zj.java.thread;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zhoujun
 * @Date: 2021/3/19 9:55
 * @Description:
 */
public class AtomicityThread {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    Lock lock = new ReentrantLock();    //注意这个地方
    public static void main(String[] args) {
        final AtomicityThread test = new AtomicityThread();
        new Thread(() -> test.insert(Thread.currentThread())).start();
        new Thread(() -> test.insert(Thread.currentThread())).start();
    }

    public void insert(Thread thread) {
//        Lock lock = new ReentrantLock();    //注意这个地方
        lock.lock();
        try {
            System.out.println(thread.getName() + "得到了锁");
            for (int i = 0; i < 5; i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println(thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

//    public static void main(String[] args) {
//        TestThread testThread = new TestThread();
//        for (int i = 0; i < 2000; i++) {
//            Thread thread = new Thread(testThread);
//            thread.start();
//        }
//    }
}

class TestThread implements Runnable {

    int n = 0;

    @Override
    public void run() {
        synchronized (this) {
            n++;
            System.out.println("n = " + n);
        }
    }
}

class TestThread2 implements Runnable, Lock {

    @Override
    public void run() {

    }

    /**
     * 首先lock()方法是平常使用得最多的一个方法，
     * 就是用来获取锁。如果锁已被其他线程获取，则进行等待。
     * 由于在前面讲到如果采用Lock，
     * 必须主动去释放锁，并且在发生异常时，
     * 不会自动释放锁。因此一般来说，
     * 使用Lock必须在try{}catch{}块中进行，
     * 并且将释放锁的操作放在finally块中进行，
     * 以保证锁一定被被释放，防止死锁的发生。
     * 通常使用Lock来进行同步的话，是以下面这种形式去使用的：
     * Lock lock = ...;
     * lock.lock();
     * try{
     * //处理任务
     * }catch(Exception ex){
     * }finally{
     * lock.unlock();   //释放锁
     * }
     */
    @Override
    public void lock() {

    }

    /**
     * lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，
     * 如果线程正在等待获取锁，则这个线程能够响应中断，
     * 即中断线程的等待状态。也就使说，
     * 当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，
     * 假若此时线程A获取到了锁，而线程B只有在等待，
     * 那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程。
     * <p>
     * 　　由于lockInterruptibly()的声明中抛出了异常，
     * 所以lock.lockInterruptibly()必须放在try块中或者在调用
     * lockInterruptibly()的方法外声明抛出InterruptedException。
     *
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * tryLock()方法是有返回值的，它表示用来尝试获取锁，
     * 如果获取成功，则返回true，
     * 如果获取失败（即锁已被其他线程获取）
     *
     * @return
     */
    @Override
    public boolean tryLock() {
        return false;
    }

    /**
     * tryLock(long time, TimeUnit unit)方法和tryLock()方法是类似的，
     * 只不过区别在于这个方法在拿不到锁时会等待一定的时间，
     * 在时间期限之内如果还拿不到锁，就返回false。
     * 如果如果一开始拿到锁或者在等待期间内拿到了锁，则返回true。
     *
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
