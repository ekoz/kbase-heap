/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.thread;

/**
 * 锁方法是 flags ACC_SYNCHRONIZED
 *
 * 锁代码块
 * monitorenter 进入并获得对象监视器
 * monitorexit 释放并退出对象监视器
 *
 * https://www.cnblogs.com/tojian/p/9949767.html
 *
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/16 12:40
 */
public class SyncDemo {

    class MyCount{
        Integer i = 0;

        public synchronized int add(){
            //  flags: ACC_PUBLIC, ACC_SYNCHRONIZED
            return i++;
        }
    }

    class MyThread implements Runnable{

        MyCount myCount;

        MyThread(MyCount myCount){
            this.myCount = myCount;
        }

        @Override
        public void run() {
            myCount.add();
        }

    }

    class MyThread2 implements Runnable{

        @Override
        public void run() {
            // 锁代码块会有 monitorenter 和 monitorexit 的指令
            synchronized (this){
                for (int i=0;i<10;i++){
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncDemo demo = new SyncDemo();
        MyCount myCount = demo.new MyCount();
        System.out.println(myCount.add());

        for (int i=0;i<1000;i++){
            Thread t = new Thread(demo.new MyThread(myCount));
            t.start();
        }

        Thread.sleep(1*1000);

        System.out.println(myCount.add());

        Thread t1 = new Thread(demo.new MyThread2());
        t1.start();
    }
}
