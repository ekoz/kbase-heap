/*
 * Powered by https://zhengxinacc.com
 */
package com.eastrobot.heap.prac.leetcode.P26_删除排序数组中的重复项;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/02/23 22:00
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j=1;j<nums.length;j++){
            if (nums[i]!=nums[j]){
                nums[i+1] = nums[j];
                i++;
            }
        }
        return i+1;
    }

    public static void main(String[] args) {
        long started = System.currentTimeMillis();
//        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        int[] nums = new int[]{1,1,2};
        int result = new Solution().removeDuplicates(nums);
        System.out.println("interval: " + (System.currentTimeMillis() - started));
        System.out.println(result);

        for (int i=0;i<result;i++){
            System.out.println(nums[i]);
        }
    }
}