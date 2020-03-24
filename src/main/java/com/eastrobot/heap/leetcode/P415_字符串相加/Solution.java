/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.P415_字符串相加;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * 注意：
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 * https://leetcode-cn.com/problems/add-strings/
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-21 10:24
 */
class Solution {

    public String addStrings(String num1, String num2) {
        return "";
    }

    /**
     * 长度过长会导致溢出
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings1(String num1, String num2) {
        return num1.length()>num2.length()?add(num1, num2):add(num2, num1);
    }

    /**
     * num1 长度必须大于 num2
     * @param num1
     * @param num2
     * @return
     */
    private String add(String num1, String num2){
        int distence = num1.length()>num2.length()?num1.length()-num2.length():num2.length()-num1.length();
        int sum = 0;
        for (int i=num1.length()-1;i>=0;i--){
            int n1 = num1.charAt(i) - '0';
            int n2 = 0;
            if (i>=distence){
                n2 = num2.charAt(i-distence) - '0';
            }
            int square = 1;
            for (int j=0;j<num1.length()-i-1;j++){
                square = 10*square;
            }
            System.out.println(n1 +", " + n2 + ", " + square + ", " + (n1+n2)*square);
            sum += (n1+n2)*square;
        }
        System.out.println(sum);
        return String.valueOf(sum);
    }

    public static void main(String[] args) {
//        String num1 = "88";
//        String num2 = "520";
        String num1 = "6913259244";
        String num2 = "71103343";
        long started = System.currentTimeMillis();
        String result = new Solution().addStrings(num1, num2);
        System.out.println("interval: " + (System.currentTimeMillis() - started));
        System.out.println(result);
        System.out.println(Integer.valueOf(num1)+Integer.valueOf(num2));

        System.out.println('5' - '0');

        System.out.println(1000000000*6);
    }
}


