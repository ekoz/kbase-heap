/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.PM40_最小的k个数;

import java.util.Arrays;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-20 13:07
 */
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        quickSelect(arr, 0, arr.length-1, k);
        return Arrays.copyOfRange(arr, 0, k);
//            return new int[0];
    }

    private void quickSelect(int[] arr, int start, int end, int k){
        if (start==end) return;

        int pivot = arr[start];

        int i=start, j=end;

        // 如果哨兵1<哨兵2
        while(i<=j){
            // 左侧数据始终小于指定数据，那么哨兵1向右移动
            while(i<=j && arr[i]<pivot) i++;
            // 右侧数据始终大于指定数据，那么哨兵2向左移动
            while(i<=j && arr[j]>pivot) j--;
            // 只要满足哨兵1即将靠近哨兵2，那么进行数据交换
            if (i<=j){
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        System.out.println(i + ", "+ j);
        printArr(arr);

        if (start<=j){
            quickSelect(arr, start, j, k);
        }
        if (i<=end){
            quickSelect(arr, i, end, k);
        }

    }

    private void swap(int[]arr, int i, int j){
        int tmp = arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    private void printArr(int[] arr){
        for (int i : arr){
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 1, 2, 5, 6, 7, 8, 9 ,4, 23, 1};
        long started = System.currentTimeMillis();
        int[] result = new Solution().getLeastNumbers(nums, 3);
        System.out.println("interval: " + (System.currentTimeMillis() - started));
//        System.out.println(result);
        for (int i : result){
            System.out.print(i + ", ");
        }

    }
}