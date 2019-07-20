/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.weekly01;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/12 14:48
 * https://leetcode-cn.com/problems/palindrome-number/
 */
public class PalindromeNumber {

    class Solution {
        public boolean isPalindrome0(int x) {
            if (x<0) return false;
            String s = String.valueOf(x);
            int left =0;
            int right = s.length()-1;
            while(left<right){
                if (s.charAt(left)!=s.charAt(right)){
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }

        /**
         * 如果 x 逆转后的数字等于x，那么 x就是回文数
         * @param x
         * @return
         */
        public boolean isPalindrome(int x) {
            if (x<0) return false;

            int sum = 0;
            int num = x;
            while(num!=0){
                sum = sum*10 + num%10;
                num = num/10;
            }

            if (sum==x) return true;
            return false;
        }
    }


    public static void main(String[] args) {
        long started = System.currentTimeMillis();
        int x = 10;
        Boolean b = new PalindromeNumber().new Solution().isPalindrome(x);
        System.out.println("interval: " + (System.currentTimeMillis() - started));
        System.out.println(b);
    }
}
