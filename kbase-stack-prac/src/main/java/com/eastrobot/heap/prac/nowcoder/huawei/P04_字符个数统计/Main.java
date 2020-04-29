/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.prac.nowcoder.huawei.P04_字符个数统计;

import java.util.Scanner;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-21 21:05
 */
public class Main {

    private static int getCount(String s){
        int[] arr = new int[127];
        for (char c : s.toCharArray()){
            arr[c]++;
        }
        int sum = 0;
        for (int i : arr){
            if (i>0) sum++;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            System.out.println(getCount(s));
        }
    }
}
