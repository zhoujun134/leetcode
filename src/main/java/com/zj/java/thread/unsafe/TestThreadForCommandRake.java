package com.zj.java.thread.unsafe;

/**
 * @Author: zhoujun
 * @Date: 2023/2/8 22:56
 */
public class TestThreadForCommandRake {
    private static boolean ready = false;
    private static int num = 0;

    static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (ready) {
                    System.out.println(num + num);
                }
                System.out.println("read Thread ...... ");
            }
        }
    }

    static class WriteThread extends Thread {
        @Override
        public void run() {
            num = 2;
            ready = true;
            System.out.println("write Thread ...... ");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        readThread.start();
        WriteThread writeThread = new WriteThread();
        writeThread.start();
        Thread.sleep(10);
        readThread.interrupt();
        System.out.println("main exit");
    }
}


