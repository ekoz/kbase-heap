/*
 * Powered by http://www.ibothub.com
 */
package com.eastrobot.heap.prac.leetcode.P290_单词规律;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/03/09 21:40
 * https://leetcode-cn.com/problems/word-pattern/
 */
class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] arr = s.split(" ");

        Map<Object, Integer> map = new HashMap<>();

        if (pattern.length()!=arr.length){
            return false;
        }
        if (arr.length==1){
            return true;
        }
        for (int i=0;i<arr.length;i++){
            if (map.put(pattern.charAt(i), i)!=map.put(arr[i], i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[][] arr = new String[][]{
                new String[]{"abba", "dog cat cat dog"}
                , new String[]{"abba", "dog cat cat fish"}
                , new String[]{"aaaa", "dog cat cat dog"}
                , new String[]{"abba", "dog dog dog dog"}
                , new String[]{"abc", "b c a"}
        };
        for (String[] item : arr){
            long started = System.currentTimeMillis();
            String pattern = item[0];
            String s = item[1];
            boolean b = new Solution().wordPattern(pattern, s);
            System.out.println("interval: " + (System.currentTimeMillis() - started));
            System.out.println(b);
        }




    }
}