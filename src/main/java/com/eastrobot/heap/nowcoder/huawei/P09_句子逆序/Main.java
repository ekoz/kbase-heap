/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.nowcoder.huawei.P09_句子逆序;

import java.util.Scanner;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-21 21:05
 */
public class Main {

    /**
     * 反转句子
     *
     * @param sentence 原句子
     * @return 反转后的句子
     */
    public static String[] reverse(String sentence){
        String[] arr = sentence.split("\\s+");
        for (int i=0,j=arr.length-1;i<j;i++,j--){
            String tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            String[] reverse = reverse(s);
            for (String t : reverse){
                System.out.print(t + " ");
            }

        }
    }
}
