/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.leetcode;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/15 16:07
 */
public class Temp {

    class Solution {
        public int romanToInt(int s) {
            return 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0};
        for (int num : nums){
            long started = System.currentTimeMillis();
            int result = new Temp().new Solution().romanToInt(num);
            System.out.println("interval: " + (System.currentTimeMillis() - started));
            System.out.println(result);
        }

    }
}
