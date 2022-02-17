package com.eastrobot.heap.thread;

/**
 * 双重检测锁
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/2/17 20:28
 */
public class DoubleCheckLockSingleton {

    private static DoubleCheckLockSingleton instance = null;


    private DoubleCheckLockSingleton(){

    }

    /**
     * 注意，24和21有可能调换顺序执行，同样遵循 as-if-serial 和 before-happened
     *
     * 如果 24 和 21 调换顺序执行，线程A执行24完毕后，线程B进来了，此时线程B认为 instance 不为null，就直接 return 了
     *
     * 对象的半初始化问题
     *
     * 通过加  volatile 来实现顺序执行，底层来实现内存屏障，加了 volatile，相当于告诉了cpu，不要进行重排序
     *
     * 10 monitorenter
     * 11 getstatic #2 <com/eastrobot/heap/thread/DoubleCheckLockSingleton.instance>
     * 14 ifnonnull 27 (+13)
     * 17 new #3 <com/eastrobot/heap/thread/DoubleCheckLockSingleton>
     * 20 dup
     * 21 invokespecial #4 <com/eastrobot/heap/thread/DoubleCheckLockSingleton.<init>>
     * 24 putstatic #2 <com/eastrobot/heap/thread/DoubleCheckLockSingleton.instance>
     * 27 aload_0
     * 28 monitorexit
     * @return
     */
    public static DoubleCheckLockSingleton getInstance(){

        if (instance==null) {
            synchronized (DoubleCheckLockSingleton.class){
                if (instance==null) {
                    instance = new DoubleCheckLockSingleton();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {
        DoubleCheckLockSingleton instance = DoubleCheckLockSingleton.getInstance();
        System.out.println(instance);
    }

}
