package com.eastrobot.heap.prac.zuo.class01;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/6/28 20:42
 */
public class CodePrint01 {

    private static void print(int num){

        // 一个数左移1位的值，就等于这个数乘以2

        for (int i=31; i>=0; i--){
            System.out.print((num & 1<<i)==0? 0: 1);
        }
        System.out.println( );

    }

    public static void main(String[] args) {
        // 00000000000000000000000000000100
        print(4);

        // 00000000000000000000000000001000
        print(8);

        // 00000000000000000000000000000001
        print(1);

        // 11111111111111111111111111111111
        // 第一位是符号，1标识是负数
        // 负数剩余31位取反，那么就是0000000000000000000000000000000，也就是0，取反的符号就是 ~，例如~1
        // 负数最终要加1，所以就是-1
        // 反码，补码

        // 补码的表示方法是：正数的补码就是其本身，负数的补码是在其原码的基础上，符号位不变，其余各位取反，最后+1 (即在反码的基础上+1)。
        //
        // [+1] = [00000001](原码) = [00000001](反码) = [00000001](补码)
        //
        // [-1] = [10000001](原码) = [11111110](反码) = [11111111](补码)
        print(-1);

        // 01111111111111111111111111111111
        print(Integer.MAX_VALUE);

        // 10000000000000000000000000000000
        // 为什么这个数最小
        // 第一位是1，那么就是负数
        // 剩余31位，全部取反，那么就是 1111111111111111111111111111111，31个1
        // 31个1加1，每一位都会变成0，最终会进一位，变成 1000...31个0，这个数是2的31次方，即 2,147,483,648
        // 所以最小值是-2的31次方，-2,147,483,648
        print(Integer.MIN_VALUE);

        // 反码运算
        print(~1);
    }
}