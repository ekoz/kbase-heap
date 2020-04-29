/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.thread;

import java.util.concurrent.Semaphore;

/**
 * 控制某组资源的并发访问
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/14 15:52
 */
public class SemaphoreDemo {

    static int size = 5;
    static Semaphore semaphore = new Semaphore(size);

    class MyThread implements Runnable{
        @Override
        public void run() {
            try {
                semaphore.acquire();
                // 明显能看到输出五条数据后，再输出剩余的五条数据
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }

        }
    }

    public static void main(String[] args) {
        SemaphoreDemo demo = new SemaphoreDemo();
        for (int i=0;i<size*2;i++){
            Thread t = new Thread(demo.new MyThread());
            t.start();
        }

    }
}
