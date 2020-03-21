/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.nowcoder.huawei.P07_合并表记录;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/completeSubmissionInfo?submissionId=70413869&tagId=null&subTagId=3
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-21 21:05
 */
public class Main {

    static Map<Integer, Integer> map = new HashMap<>();

    private static void merge(String s){
        if (s.length()==0) return;
        String[] arr = s.split("\\s+");
        Integer key = Integer.valueOf(arr[0]);
        Integer value = Integer.valueOf(arr[1]);
        if (map.get(key)==null){
            map.put(key, value);
        }else{
            map.put(key, map.get(key)+value);
        }


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer size = Integer.valueOf(scanner.nextLine());
//        System.out.println(size);
        for (int i=0;i<size;i++){
            merge(scanner.nextLine());
        }
        map.forEach((k, v)->System.out.println(k + " " +v));
    }
}
