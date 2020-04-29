/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 类似于一个秒表，用于记录线程到达的数量，如果线程都到达，那么再执行 cd.wait() 后面的内容
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/14 13:57
 */
public class CyclicBarrierDemo {

    static int size = 10;
    static CyclicBarrier cb = new CyclicBarrier(size, () -> System.out.println("所有线程都到齐，处理其他内容"));

    class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                // 线程要处理的主体内容
                System.out.println(Thread.currentThread().getName() + " 到达");
                Thread.sleep(1000);
                cb.await();
                System.out.println(Thread.currentThread().getName() + " 出发");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrierDemo demo = new CyclicBarrierDemo();

        for (int i=0;i<size;i++){
            Thread t = new Thread(demo.new MyThread());
            t.start();
        }
    }
}
