/*
 * Powered by https://zhengxinacc.com
 */
package com.eastrobot.heap.prac.leetcode.P28_实现strStr;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/02/21 22:14
 * https://leetcode-cn.com/problems/implement-strstr/
 */
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length()>haystack.length()){
            return -1;
        }
        if (haystack.length() == needle.length() && haystack.length()==0){
            return 0;
        }
        if (needle.length()==0){
            return 0;
        }

//        int j=0;
        for (int i=0;i<haystack.length();i++){
//            while (haystack.charAt(i)==needle.charAt(j)){
//                j++;
//                i++;
//                if (j==needle.length()){
//                    return i-j;
//                }
//            }
            if (haystack.length()-i>=needle.length()){
                int j=0;
                for (;j<needle.length();j++){
                    if (haystack.charAt(i)!=needle.charAt(j)){
                        if (j>2){
                            i = i-2;
                        }
                        break;
                    }
                    i++;
                }
                if (j==needle.length()){
                    return i-j;
                }
            }else{
                break;
            }
        }

        return -1;
    }

    public int strStr2(String haystack, String needle) {
        if (needle.length()>haystack.length()){
            return -1;
        }
        if (haystack.length() == needle.length() && haystack.length()==0){
            return 0;
        }
        if (needle.length()==0){
            return 0;
        }

        for (int i=0;i<haystack.length();i++){
            if (haystack.startsWith(needle, i)){
                return i;
            }
        }

        return -1;
    }

    public int strStr1(String haystack, String needle) {
        if (needle.length()>haystack.length()){
            return -1;
        }
        if (haystack.length() == needle.length() && haystack.length()==0){
            return 0;
        }
        if (needle.length()==0){
            return 0;
        }

        int j=0;
        for (int i=0;i<haystack.length();i++){
            while (haystack.charAt(i)==needle.charAt(j)){
                j++;
                i++;
                if (j==needle.length()){
                    return i-j;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String[][] arr = new String[][]{new String[]{"hello", "ll"}, new String[]{"aaaaa", "bba"}
                , new String[]{"", ""}, new String[]{"a", ""}, new String[]{"mississippi", "issipi"}
                , new String[]{"mississippi", "issip"}, new String[]{"mississippi", "issip"}
                , new String[]{"mississippi", "pi"}};

        for (int i=0;i<arr.length;i++){
            long started = System.currentTimeMillis();
            String haystack = arr[i][0];
            String needle = arr[i][1];
            int result = new Solution().strStr2(haystack, needle);
            System.out.println("interval: " + (System.currentTimeMillis() - started));
            System.out.println(result);
        }

    }
}