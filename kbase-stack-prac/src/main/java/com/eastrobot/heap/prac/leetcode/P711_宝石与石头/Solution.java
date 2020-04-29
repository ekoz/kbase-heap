/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.prac.leetcode.P711_宝石与石头;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/28 18:16
 * https://leetcode-cn.com/problems/jewels-and-stones/
 */
class Solution {
    public int numJewelsInStones(String J, String S) {
        int sum=0;
        for (char c : J.toCharArray()){
            for (int i=0;i<S.length();i++){
                if (S.charAt(i)==c){
                    sum++;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        String J = "";
        String S = "";
        J = "aA";
        S = "aAAbbbb";
        J = "z";
        S = "ZZ";
        long started = System.currentTimeMillis();
        int result = new Solution().numJewelsInStones(J, S);
        System.out.println("interval: " + (System.currentTimeMillis() - started));
        System.out.println(result);

    }
}
