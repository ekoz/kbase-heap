/*
 * powered by http://ibothub.com
 */
package com.eastrobot.heap.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * volatile 有序性验证
 * @author <a href="mailto:eko.zhan@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/2/10 21:13
 */
public class VolatileSerialTests {


    static int x, y, a, b;


    public static void main(String[] args) throws InterruptedException {

        Set<String> resultSet = new HashSet<>();

        for (int i=0;i<100000000;i++){
            x = 0;
            y = 0;
            a = 0;
            b = 0;


            Thread one = new Thread(()->{
               a = y;
               x = 1;
            });

            Thread two = new Thread(()->{
               b = x;
               y = 1;
            });

            one.start();
            two.start();

            one.join();
            two.join();

//[a=0,b=0, a=1,b=0, a=0,b=1, a=1,b=1]
//[a=0,b=0, a=1,b=0, a=0,b=1, a=1,b=1]
//[a=0,b=0, a=1,b=0, a=0,b=1, a=1,b=1]
//[a=0,b=0, a=1,b=0, a=0,b=1, a=1,b=1]
            // 为什么会存在 a=1,b=1，两个线程同时执行时，cpu 先执行x=1，y=1，然后再进行了赋值，cpu进行了指令重排序
            // 重排序规则：as-if-serial 和 happens-before
            resultSet.add("a=" + a + ",b=" + b);
            System.out.println(resultSet);


        }

    }

}
