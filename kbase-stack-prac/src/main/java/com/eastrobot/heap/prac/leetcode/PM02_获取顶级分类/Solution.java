/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.prac.leetcode.PM02_获取顶级分类;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/17 16:04
 */
public class Solution {

    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        Set<String> set2 = new TreeSet<>();

        set.add("1000.1003.1001L");
        set.add("1000.1003.1002L");
        set.add("1000.1003.1003L");
        set.add("1000.1004");
        set.add("1000.1004.1000");
        set.add("1000.1004.1000.1000L");
        set.add("1000.1004.1000.1001L");
        set.add("1000.1004.1000.1002L");

//        set.forEach(s -> System.out.println(s));

        String tmp = "";
        for (String s : set){
            if ("".equals(tmp)){
                set2.add(s);
                tmp = s;
            }else if (!s.startsWith(tmp)){
                set2.add(s);
                tmp = s;
            }
        }

        set2.forEach(s -> System.out.println(s));


    }
}
