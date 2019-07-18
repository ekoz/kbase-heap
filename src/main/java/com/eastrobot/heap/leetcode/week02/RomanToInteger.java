/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.week02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/15 16:48
 */
public class RomanToInteger {
    class Solution {
        public int romanToInt(String s) {
            Map<String, Integer> dmap = new HashMap<String, Integer>(){
                {
                    put("IV", 4);
                    put("IX", 9);
                    put("XL", 40);
                    put("XC", 90);
                    put("CD", 400);
                    put("CM", 900);
                }
            };
            Map<String, Integer> map = new HashMap<String, Integer>(){
                {
                    put("I", 1);
                    put("V", 5);
                    put("X", 10);
                    put("L", 50);
                    put("C", 100);
                    put("D", 500);
                    put("M", 1000);
                }
            };

            int sum = 0;
            for (int i=0;i<s.length();){
                String dkey = "";
                if (i+2>s.length()){
                    dkey = s.substring(i, i+1);
                }else{
                    dkey = s.substring(i, i+2);
                }
                String key = s.substring(i, i+1);
                if (dmap.containsKey(dkey)){
                    sum = sum + dmap.get(dkey);
                    i=i+2;
                }else if (map.containsKey(key)){
                    sum = sum + map.get(key);
                    i=i+1;
                }else{
                    i++;
                }
            }

            return sum;
        }

    }

    public static void main(String[] args) {
        long started = System.currentTimeMillis();
        String s = "LVIII";
        s = "MCMXCIV";
//        s = "IV";
        int result = new RomanToInteger().new Solution().romanToInt(s);
        System.out.println("interval: " + (System.currentTimeMillis() - started));
        System.out.println(result);
    }
}
