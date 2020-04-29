/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.prac.leetcode.P07_整数反转;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/11 9:42
 * https://leetcode-cn.com/problems/reverse-integer/
 */
class Solution {
    public int reverse0(int x) {

        Boolean lowerZero = false;
        if (x < 0) {
            lowerZero = true;
            x = Math.abs(x);
        }
        String s = String.valueOf(x);
        char[] c = new char[s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            c[s.length() - 1 - i] = s.charAt(i);
        }
        int result = 0;
        try{
            if (lowerZero) {
                result = 0 - Integer.valueOf(new String(c));
            } else {
                result = Integer.valueOf(new String(c));

            }
        }catch (Exception e){
            result = 0;
        }

        if ( result < Math.pow(-2, 31) || result > (Math.pow(2, 31) - 1) ) {
            return 0;
        }
        return result;
    }

    /**
     * 109 --> 91 error
     * @param x
     * @return
     */
    public int reverse1(int x) {
        Boolean lowerZero = false;
        if (x < 0) {
            lowerZero = true;
            x = Math.abs(x);
        }
        int base = Double.valueOf(Math.pow(10, String.valueOf(x).length()-1)).intValue();

        int y = 0;
        int loop = 1;
        while(x/base!=0){
            y = x/base*loop + y;
            x = x%base;
            base = Double.valueOf(Math.pow(10, String.valueOf(x).length()-1)).intValue();
            loop=loop*10;
        }
        if (lowerZero){
            y = 0-y;
        }
        if ( y < Math.pow(-2, 31) || y > (Math.pow(2, 31) - 1) ) {
            return 0;
        }
        return y;
    }

    public int reverse(int x) {
        Boolean lowerZero = false;
        if (x < 0) {
            lowerZero = true;
            x = Math.abs(x);
        }
        int y = 0;
        while (x!=0){
            int pop = x%10;
            y = y*10 + pop;
            x = x/10;
        }
        System.out.println(y);
        if (lowerZero){
            y = 0-y;
        }
        if ( y < Math.pow(-2, 31) || y > (Math.pow(2, 31) - 1) ) {
            return 0;
        }
        return y;
    }

    public static void main(String[] args) {
        int x = 234;
        x = 1534236469;
//        x = 1928739999;
//        x = 901000;
//        x = 120;
//        x = -123;
        long started = System.currentTimeMillis();
        int target = new Solution().reverse(x);
        System.out.println("interval: " + (System.currentTimeMillis()-started));
        System.out.println(target);

    }
}
