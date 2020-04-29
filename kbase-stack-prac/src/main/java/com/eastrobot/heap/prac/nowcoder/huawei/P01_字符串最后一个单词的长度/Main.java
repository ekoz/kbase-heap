/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.prac.nowcoder.huawei.P01_字符串最后一个单词的长度;

import java.util.Scanner;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-21 21:04
 */
public class Main {
    public static int getLastWordLength(String s){
        return s.length() - s.lastIndexOf(" ") - 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext())
        {
            String s = scanner.nextLine();
            System.out.println(getLastWordLength(s));
        }

    }
}
