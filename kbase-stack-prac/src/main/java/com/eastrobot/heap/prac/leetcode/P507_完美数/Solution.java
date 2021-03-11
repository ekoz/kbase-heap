/*
 * Powered by http://www.ibothub.com
 */
package com.eastrobot.heap.prac.leetcode.P507_完美数;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/03/11 20:36
 * https://leetcode-cn.com/problems/perfect-number/
 */
class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num<0){
            return false;
        }
        int sum = 0;
        for (int i=1;i<=num/2;i++){
            if (num%i==0){
//                System.out.println(i);
                sum += i;
            }
        }
        return sum==num;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{28, 6, 496, 8128, 2, 99999999};
        for (int n : arr){
            long started = System.currentTimeMillis();
            System.out.println(new Solution().checkPerfectNumber(n));
            System.out.println("interval: " + (System.currentTimeMillis() - started));
        }
    }
}