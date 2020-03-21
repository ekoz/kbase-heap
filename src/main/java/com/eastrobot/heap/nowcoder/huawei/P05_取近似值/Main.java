/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.nowcoder.huawei.P05_取近似值;

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
            float f = scanner.nextFloat();
            System.out.println(Math.round(f));
        }
    }
}
