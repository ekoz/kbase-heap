/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.proxy;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version 1.0
 * @date 2019/4/9 14:12
 */
public class StaticAgent {

    interface Person {
        void sayHi();
    }

    static class Man implements Person {
        private String name;

        Man(String name){
            this.name = name;
        }

        @Override
        public void sayHi() {
            System.out.println(this.name + "在向你打招呼");
        }
    }

    static class Proxy implements Person {
        private Man man;

        Proxy(Person man){
            this.man = (Man)man;
        }

        @Override
        public void sayHi() {
            System.out.println("准备打招呼");
            this.man.sayHi();
            System.out.println("结束打招呼");
        }
    }

}
