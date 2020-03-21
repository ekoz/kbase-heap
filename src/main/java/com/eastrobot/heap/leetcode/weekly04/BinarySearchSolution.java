/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.weekly04;

/**
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * https://leetcode-cn.com/problems/binary-search/
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-21 14:24
 */
public class BinarySearchSolution {

    class Solution {
        public int search(int[] nums, int target) {
            return binarySearch(nums, 0, nums.length-1, target);
        }

        private int binarySearch(int[] nums, int start, int end, int target){
            if (start>end) return -1;

            int mid = (end-start)/2+start;
            System.out.println(start + ", " + mid + ", " + end);

            if (nums[mid]>target){
                return binarySearch(nums, start, mid-1, target);
            }else if (nums[mid]<target){
                return binarySearch(nums, mid+1, end, target);
            }
            return mid;
        }
    }

    public static void main(String[] args) {

//        int[] nums = new int[]{-1,0,3,5,9,12};
//        int[] nums = new int[]{5};
//        int target = 5;
        int[] nums = new int[]{-1,0,3,5,9,12};
        int target = -1;

        Solution solution = new BinarySearchSolution().new Solution();
        System.out.println(solution.search(nums, target));
    }

}
