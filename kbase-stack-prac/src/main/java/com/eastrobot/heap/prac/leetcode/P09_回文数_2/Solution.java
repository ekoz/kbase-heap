/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.prac.leetcode.P09_回文数_2;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/15 16:07
 *
 */
class Solution {
    public Boolean romanToInt(String s) {

        int[] counts = new int[128];
        char[] chars = s.toCharArray();

        for (char c : chars){
            counts[c]++;
        }

        int size = 0;
        for (int i : counts){
            if (i%2==1){
                size++;
                if (size>1){
                    return Boolean.FALSE;
                }
            }
        }

//            for (int i=0,j=s.length()-1;i<j;i++,j--){
//                if (s.charAt(i)!=s.charAt(j)){
//                    return Boolean.FALSE;
//                }
//            }
        return Boolean.TRUE;
    }

    public static void main(String[] args) {
        String[] nums = new String[]{"abcc", "aaba", "ccd", "ccdd"};
        for (String num : nums){
            long started = System.currentTimeMillis();
            Boolean result = new Solution().romanToInt(num);
            System.out.println("interval: " + (System.currentTimeMillis() - started));
            System.out.println(result);
        }

    }
}
