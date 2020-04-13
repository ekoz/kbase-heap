/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.thread;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/18 12:28
 */
public class DeadLockDemo {

    static Object a = new Object();
    static Object b = new Object();


    public static void main(String[] args) {
        deadlock();
//        reentrant();
    }

    /**
     * synchronized 是可重入锁，所以这里不会造成死锁
     * 如何实现？
     * synchronized 对象头MarkWord中记录线程，ReentrantLock 是通过一个AQS记录currentThread来实现可重入(state++)
     */
    private static void reentrant(){
        new Thread(()->{
            synchronized (a){
                try {
                    Thread.sleep(1000);
                    synchronized (a){
                        System.out.println("a->b");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            synchronized (a){
                synchronized (a){
                    try {
                        Thread.sleep(1000);
                        System.out.println("b->a");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private static void deadlock(){
        new Thread(()->{
            synchronized (a){
                try {
                    Thread.sleep(1000);
                    synchronized (b){
                        System.out.println("a->b");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            synchronized (b){
                synchronized (a){
                    try {
                        Thread.sleep(1000);
                        System.out.println("b->a");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
