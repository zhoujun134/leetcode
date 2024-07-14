package com.zj.java.thread.thread相关.notifyWait;

public class TestNotifyNotifyAll {

    private static Object obj = new Object();

    /***
     * 等待池：假设一个线程A调用了某个对象的wait()方法，
     *        线程A就会释放该对象的锁后，进入到了该对象的等待池，
     *        等待池中的线程不会去竞争该对象的锁。
     * 锁池：只有获取了对象的锁，线程才能执行对象的 synchronized 代码，
     *       对象的锁每次只有一个线程可以获得，其他线程只能在锁池中等待
     *
     * @param args
     */
    public static void main(String[] args) {

        //测试 RunnableImplA wait()
        Thread t1 = new Thread(new RunnableImplA(obj), "thread-A1");
        Thread t2 = new Thread(new RunnableImplA(obj), "thread-A2");
        t1.start();
        t2.start();

        //RunnableImplB notify()
//        Thread t3 = new Thread(new RunnableImplB(obj));
//        t3.start();


//		//RunnableImplC notifyAll()
        Thread t4 = new Thread(new RunnableImplC(obj), "thread-C1");
        t4.start();
    }

}


class RunnableImplA implements Runnable {

    private Object obj;

    public RunnableImplA(Object obj) {
        this.obj = obj;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " => run on RunnableImplA");
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + " => obj to wait on RunnableImplA");
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " => obj continue to run on RunnableImplA");
        }
    }
}

class RunnableImplB implements Runnable {

    private Object obj;

    public RunnableImplB(Object obj) {
        this.obj = obj;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " => run on RunnableImplB");
        System.out.println(Thread.currentThread().getName() + " => 睡眠3秒...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + " => notify obj on RunnableImplB");
            obj.notify();
        }
    }
}

class RunnableImplC implements Runnable {

    private Object obj;

    public RunnableImplC(Object obj) {
        this.obj = obj;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " => run on RunnableImplC");
        System.out.println(Thread.currentThread().getName() + " => 睡眠3秒...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + " => notifyAll obj on RunnableImplC");
            obj.notifyAll();
        }
    }
}