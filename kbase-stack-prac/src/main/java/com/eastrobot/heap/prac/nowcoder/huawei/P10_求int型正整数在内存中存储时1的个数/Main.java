/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.prac.nowcoder.huawei.P10_求int型正整数在内存中存储时1的个数;

import java.util.Scanner;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-21 21:05
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int i = scanner.nextInt();
            String s = Integer.toBinaryString(i);
//            System.out.println(s);
            int[] count = new int[2];
            for (char c : s.toCharArray()){
                count[c-'0']++;
            }
//            System.out.println(count[0]);
            System.out.println(count[1]);


        }
    }
}
