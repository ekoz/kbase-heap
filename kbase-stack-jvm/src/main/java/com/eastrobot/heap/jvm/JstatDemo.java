/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.jvm;

import org.openjdk.jol.info.ClassLayout;


/**
 * jstat -gcutil
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/9 10:14
 */
public class JstatDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5*1000);


        StringBuffer sb = new StringBuffer();
        sb.append("1");


        byte[] bytes = new byte[1024*1024]; //1M
        final Thread t1 = new Thread(() -> System.out.println("MyThread"));

        System.out.println(ClassLayout.parseClass(JstatDemo.class).toPrintable());
        System.out.println("------");
        System.out.println(ClassLayout.parseInstance(sb).toPrintable());

        System.out.println(queryCount());

        for (int i=0;i<10;i++){
            String s = new String(bytes);
            System.out.println(System.currentTimeMillis() + " -> " + s.length());
            t1.sleep(1000);
        }
    }

    private static int queryCount(){
        int i=2;
        int k=4;
        return i*k;
    }
}
