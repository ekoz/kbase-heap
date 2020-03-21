/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.nowcoder.huawei.P06_提取不重复的整数;

import java.util.Scanner;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-21 21:05
 */
public class Main {

    private static void solution(String s){
        int[] intArr = new int[10];
        for (int i=s.length()-1;i>=0;i--){
            char c = s.charAt(i);
            if (intArr[c-'0']==0){
                System.out.print(c);
                intArr[c-'0']++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            solution(s);
        }
    }
}
