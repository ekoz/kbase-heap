/*
 * Powered by http://www.ibothub.com
 */
package com.eastrobot.heap.prac.leetcode.P283_移动零;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/03/09 20:15
 * https://leetcode-cn.com/problems/move-zeroes/
 */
class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        // 左边是整理好的队列
        int left = 0;
        // 右边是待整理的队列
        int right = 0;

        while (right<n){
            if (nums[right]!=0){
                // 如果待整理的队列不为0，那么交换位置
                // 且左右下标都向右移动
                swap(nums, left, right);
                left++;
            }
            // 如果等于0，那么移动右下标，直到找到不为0的数，或者结束为止
            right++;
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void main(String[] args) {
        long started = System.currentTimeMillis();
        int[] nums = new int[]{0,1,0,3,12};
        new Solution().moveZeroes(nums);
        System.out.println("interval: " + (System.currentTimeMillis() - started));

        for (int i=0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
    }
}