/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.prac.leetcode.P27_移除元素;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/02/21 22:00
 * https://leetcode-cn.com/problems/remove-element/
 */
class Solution {
    public int removeElement(int[] nums, int val) {
        int i=0;
        for (int j=0;j<nums.length;j++){
            if (nums[j]!=val){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public int removeElement2(int[] nums, int val) {
        int i=0;
        for (;i<nums.length-1;i++){
            if (nums[i]==val){
                System.arraycopy(nums, i+1, nums, i, nums.length-1-i);
                i--;
            }
        }
        return i-1;
    }

    public static void main(String[] args) {
        long started = System.currentTimeMillis();
        int[] nums = new int[]{1,2,2,3,4};
        int val = 2;
        int result = new Solution().removeElement2(nums, val);
        System.out.println("interval: " + (System.currentTimeMillis() - started));
        System.out.println(result);

        for (int i=0;i<result;i++){
            System.out.println(nums[i]);
        }
    }
}