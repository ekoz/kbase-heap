/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.weekly02;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/20 15:40
 * https://leetcode-cn.com/problems/sort-array-by-parity-ii/
 */
public class SortArrayByParityII {

    class Solution {
        public int[] sortArrayByParityII0(int[] A) {
            if (!check(A)) return A;
            int[] result = new int[A.length];

            // 奇数数组
            int[] odds = new int[A.length/2];
            // 偶数数据
            int[] evens = new int[A.length/2];

            int i=0;
            int j=0;
            for (int n : A){
                if (n%2==0){
                    evens[i++] = n;
                }else{
                    odds[j++] = n;
                }
            }
            i=0;j=0;
            for (int k=0;k<A.length;k++){
                if (k%2==0){
                    result[k] = evens[i++];
                }else{
                    result[k] = odds[j++];
                }
            }
            return result;
        }

        private boolean check(int[] A){
            if (A.length<2 || A.length>20000) return false;
            if (A.length%2==1) return false;
            // 一半奇数，一半偶数
            int sum = 0;
            for (int n : A){
                if (n%2==0){
                    sum++;
                }else{
                    sum--;
                }
            }
            if (sum!=0) return false;
            return true;
        }


        public int[] sortArrayByParityII(int[] arr) {
            if (!check(arr)) return arr;
            for (int i=0;i<arr.length;i++){
                if (i%2==0){
                    // 偶数位
                    while (arr[i]%2!=0 && i<arr.length-1){
                        int tmp = arr[i];
                        arr[i] = arr[i+1];
                        arr[i+1] = tmp;
                    }
                }else{
                    // 奇数位
                    while (arr[i]%2!=1 && i<arr.length-2){
                        int tmp = arr[i];
                        arr[i] = arr[i+1];
                        arr[i+1] = tmp;
                    }
                }
            }
            return arr;
        }
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{4,2,5,7}, {}, {3, 4}, {3,1,4,2}};
        for (int[] num : nums){
            long started = System.currentTimeMillis();
            int[] result = new SortArrayByParityII().new Solution().sortArrayByParityII(num);
            System.out.println("interval: " + (System.currentTimeMillis() - started));
            for (int n : result){
                System.out.print(n);
            }
            System.out.println();
        }

    }
}
