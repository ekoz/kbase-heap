/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.prac.nowcoder.huawei.P03_明明的随机数;

import java.util.Scanner;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-21 21:05
 */
public class Main {

    static int maxSize = 8;

    public static void solution(String s){
        if (s.length()>maxSize){
            while (s.length()>maxSize){
                System.out.println(s.substring(0, maxSize));
                s = s.substring(maxSize);
                if (s.length()<=maxSize){
                    System.out.println(sub(s));
                }
            }
        }else{
            System.out.println(sub(s));
        }
    }

    /**
     *
     * @param s
     * @return
     */
    private static String sub(String s){
        if (s.length()==maxSize){
            return s;
        }else{
            StringBuffer sb = new StringBuffer();
            for (int j=0;j<maxSize-s.length();j++){
                sb.append("0");
            }
            return s + sb.toString();
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
