/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 类似于起跑线，当等待的线程满了后，再预备开始跑，如果线程数达不到 CountDownLatch 的数量，那么会处于等待状态
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/14 12:39
 */
public class CountDownLatchDemo {

    static int size = 10;
    static CountDownLatch cdl = new CountDownLatch(size);

    class MyThread implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                cdl.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatchDemo demo = new CountDownLatchDemo();
        for (int i=0;i<size;i++){
            Thread t = new Thread(demo.new MyThread());
            t.setName("Thread-" + i);
            t.start();
        }
        cdl.await();
        long end = System.currentTimeMillis();
        // 所有线程跑完后，输出
        System.out.println("========== release(" + (end-start) + ") ========");
    }
}


