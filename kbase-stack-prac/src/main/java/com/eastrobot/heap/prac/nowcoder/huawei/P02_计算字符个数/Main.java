/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.prac.nowcoder.huawei.P02_计算字符个数;

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
            String s = scanner.nextLine();
            String c = scanner.nextLine();
            System.out.println(getCount(s.toLowerCase(), c.toLowerCase()));
        }
    }

    private static int getCount(String s1, String s2) {
        char c2 = s2.toCharArray()[0];
        int count = 0;
        for (char c : s1.toCharArray()){
            if (c==c2) count++;
        }
        return count;
    }
}
