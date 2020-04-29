/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.thread;

/**
 * 使用javap分析return和finally的执行字节码
 * https://blog.csdn.net/iteye_13391/article/details/82131265
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/18 13:26
 */
public class ReturnFinallyDemo {

    private static Integer get(Integer a, Integer b){
        try {
            return a/b;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            System.exit(0);
            System.out.println("已返回数值");
        }
        System.out.println("本句文本不会输出");
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(get(6, 2));
    }
}
