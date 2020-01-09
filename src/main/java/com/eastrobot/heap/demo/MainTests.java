/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.demo;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/9 10:14
 */
public class MainTests {

    public static void main(String[] args) throws InterruptedException {
        byte[] bytes = new byte[1024*1024]; //1M
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread);
        t1.start();

        for (int i=0;i<10;i++){
            String s = new String(bytes);
            System.out.println(System.currentTimeMillis() + " -> " + s.length());
            t1.sleep(1*30*1000);
        }
    }
}

class MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println("MyThread");
    }
}