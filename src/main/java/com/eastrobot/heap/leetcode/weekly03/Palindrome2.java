/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.weekly03;

/**
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * https://leetcode-cn.com/problems/valid-palindrome-ii/
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-20 13:07
 */
public class Palindrome2 {

    class Solution {
        public boolean validPalindrome(String s) {

            for (int i=0,j=s.length()-1;i<j;i++,j--){
                if (s.charAt(i)!=s.charAt(j)){
                    return isPalindrome(s,i+1, j) || isPalindrome(s, i, j-1);
                }
            }

            return Boolean.TRUE;
        }

        public boolean isPalindrome(String s, int start, int end) {
            for (int i=start,j=end;i<j;i++,j--){
                if (s.charAt(i)!=s.charAt(j)){
                    return Boolean.FALSE;
                }
            }
            return Boolean.TRUE;
        }
    }



    public static void main(String[] args) {
//        String[] nums = new String[]{"abc"};
//        String[] nums = new String[]{"eeccccbebaeeabebccceea"};
        String[] nums = new String[]{"aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"};
//        String[] nums = new String[]{"abc", "aba", "abca", "abbac", "abbca", "aabbc", "cabba", "ccdd", "eeccccbebaeeabebccceea"};
        for (String num : nums){
            long started = System.currentTimeMillis();
            Boolean result = new Palindrome2().new Solution().validPalindrome(num);
            System.out.println("interval: " + (System.currentTimeMillis() - started));
            System.out.println(result);
        }

    }
}
