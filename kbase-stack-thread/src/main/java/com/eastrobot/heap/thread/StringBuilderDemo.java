/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.thread;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/18 12:22
 */
public class StringBuilderDemo {


    static StringBuilder sb = new StringBuilder();
//    static StringBuffer sb = new StringBuffer();

    class MyThread implements Runnable{
        @Override
        public void run() {
            for (int i=0;i<1000;i++){
                sb.append('a');
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        StringBuilderDemo demo = new StringBuilderDemo();
        new Thread(demo.new MyThread()).start();
        new Thread(demo.new MyThread()).start();

        Thread.sleep(1000);
        System.out.println(sb.length());

    }
}
