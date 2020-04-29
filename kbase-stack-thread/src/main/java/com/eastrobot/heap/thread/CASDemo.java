/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.thread;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/19 11:09
 */
public class CASDemo {


    class CAS{

        int value;

        public synchronized int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }




    }
}
