/*
 * Powered by https://zhengxinacc.com
 */
package com.eastrobot.heap.prac.leetcode.P58_最后一个单词的长度;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/03/01 21:54
 * https://leetcode-cn.com/problems/length-of-last-word/
 */
class Solution {
    public int lengthOfLastWord(String s) {
        if (s.length()<1 || s.length()>Math.pow(10, 4)){
            throw new IllegalArgumentException("");
        }
        return s.substring(s.lastIndexOf(" ")+1).length();
    }

    public int lengthOfLastWord2(String s) {
        if (s.length()<1 || s.length()>Math.pow(10, 4)){
            throw new IllegalArgumentException("");
        }
        int count = 0;
        for (int i=s.length()-1;i>=0;i--){
            if (s.charAt(i)==' '){
                if (count==0) continue;
                break;
            }
            count++;
        }
        return count;
    }


    public static void main(String[] args) {
        String[] arr = new String[]{"Hello World", " ", "a", "a ", " a"};

        for (int i=0;i<arr.length;i++){
            long started = System.currentTimeMillis();
            String s = arr[i];
            int result = new Solution().lengthOfLastWord2(s);
            System.out.println("interval: " + (System.currentTimeMillis() - started));
            System.out.println(result);
        }

    }
}