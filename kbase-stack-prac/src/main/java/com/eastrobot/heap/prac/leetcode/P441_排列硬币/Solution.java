/*
 * Powered by http://www.ibothub.com
 */
package com.eastrobot.heap.prac.leetcode.P441_排列硬币;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/03/11 20:28
 * https://leetcode-cn.com/problems/arranging-coins/
 */
class Solution {
    public int arrangeCoins(int n) {
        int l = 0;
        for (int i=1;i<=n;i++){
            n = n-i;
            if (n<=i){
                l=i;
                break;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 8};
        for (int n : arr){
            long started = System.currentTimeMillis();
            System.out.println(new Solution().arrangeCoins(n));
            System.out.println("interval: " + (System.currentTimeMillis() - started));
        }




    }
}