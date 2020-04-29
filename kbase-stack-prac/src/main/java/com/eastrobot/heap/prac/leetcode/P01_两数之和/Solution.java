/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.prac.leetcode.P01_两数之和;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/10 15:44
 * https://leetcode-cn.com/problems/two-sum/
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i=0; i<nums.length-1; i++){
            for (int j=i+1; j<nums.length; j++){
                if (nums[i] + nums[j]==target){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] intArr = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] ints = new Solution().twoSum(intArr, target);
        System.out.println(ints);
        for (int i=0;i<ints.length;i++){
            System.out.print(ints[i]);
        }
    }
}
