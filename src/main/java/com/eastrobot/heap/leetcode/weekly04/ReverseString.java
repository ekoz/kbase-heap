/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.weekly04;


/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * https://leetcode-cn.com/problems/reverse-string/
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/15 16:07
 */
public class ReverseString {

    class Solution {
        public void reverseString(char[] s) {
            for (int i=0,j=s.length-1;i<j;i++,j--){
                char tmp = s[i];
                s[i] = s[j];
                s[j] = tmp;
            }
            System.out.println(new String(s));
        }
    }

    public static void main(String[] args) {
        String[] nums = new String[]{"hello", "Hannah"};
        for (String num : nums){
            long started = System.currentTimeMillis();
            new ReverseString().new Solution().reverseString(num.toCharArray());
            System.out.println("interval: " + (System.currentTimeMillis() - started));
        }

    }
}