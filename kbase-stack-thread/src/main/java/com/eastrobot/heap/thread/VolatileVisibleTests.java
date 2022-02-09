/*
 * powered by https://megvii.com
 */
package com.eastrobot.heap.thread;

/**
 * @author <a href="mailto:zhanzhao@megvii.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/2/9 21:00
 */
public class VolatileVisibleTests {

    /**
     * 不加 volatile，程序会结束吗？
     * 必须不会，加了 volatile，线程才会把 initFlag 刷新至主内存，主内存才会同步到其他线程
     * MESI 缓存一致性协议，总线嗅探机制，把总线当作消息队列来理解
     *
     * lock add dword ptr [rsp] ; *putstatic
     *
     */
    private static volatile boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            System.out.println("waiting data");

            while (!initFlag){

            }
            System.out.println("received data successed.");
        }).start();

        Thread.sleep(2000);

        new Thread(()->{
            prepareData();
        }).start();
    }

    private static void prepareData() {

        System.out.println("prepare data");

        // 加了 volatile 修饰符，cpu 会优先把 initFlag 刷回主内存
        // 总线嗅探到以后，其他线程会将工作内存中的变量失效，然后从主内存中重新拉取最新的值
        initFlag = true;

        System.out.println("prepared data successed.");

    }
}
