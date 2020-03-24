/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.P14_最长公共前缀;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/16 17:57
 * https://leetcode-cn.com/problems/longest-common-prefix/
 */
class Solution {
    /**
     * 执行用时 : 89 ms, 在所有 Java 提交中击败了5.33%的用户
     * @param strs
     * @return
     */
    public String longestCommonPrefix0(String[] strs) {
        if (strs.length==0) return "";
        List<String> list = Arrays.asList(strs);
        list = list.stream().sorted((o1, o2) -> o1.length() > o2.length() ? 1 : -1).collect(Collectors.toList());

        String result = "";

        System.out.println(list);

        char[] chars = list.get(0).toCharArray();

        for (int i=0;i<chars.length;i++){
            char c = chars[i];
            boolean flag = true;
            for (int j=0;j<list.size();j++){
//                    System.out.println("====================");
//                    System.out.println(c + "-" + i + "-" + (list.get(j).charAt(i)) + "-" + (list.get(j).charAt(i)!=c));
//                    System.out.println(list.get(j));
//                    System.out.println("====================");
                if (list.get(j).charAt(i)!=c){
                    flag=false;
                    break;
                }
            }
            if (flag){
                result += String.valueOf(c);
            }else{
                break;
            }
        }
        return result;
    }

    /**
     * 执行用时 :<span>13 ms</span>, 在所有 Java 提交中击败了<span>6.27%</span> 的用户
     */
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length==0) return "";
        if (strs.length==1) return strs[0];
        String result = "";

        if (strs.length<3){
            result = getCommonPrefix(strs[0], strs[1]);
        }else{
            result = longestCommonPrefix1(compress(strs));
        }

        return result;
    }

    /**
     * 对半压缩数组
     * @param strs
     * @return
     */
    private String[] compress(String[] strs){
        String[] arr = new String[strs.length/2];
        if (strs.length/2==1){
            arr = new String[strs.length/2+1];
        }
        int leftPos = 0;
        int rightPos = strs.length-1;
        while (leftPos<rightPos){
            arr[leftPos] = getCommonPrefix(strs[leftPos], strs[rightPos]);
            leftPos++;
            rightPos--;
        }
        if (strs.length/2==1){
            arr[leftPos] = strs[strs.length/2];
        }
        return arr;
    }

    private String getCommonPrefix0(String s0, String s1){
        String result = "";
        if (s0.length()>s1.length()){
            String tmp = s0;
            s0 = s1;
            s1 = tmp;
        }
        for (int i=0;i<s0.toCharArray().length;i++){
            if (s1.charAt(i)==s0.charAt(i)){
                result += String.valueOf(s0.charAt(i));
            }else{
                break;
            }
        }
        return result;
    }

    private String getCommonPrefix(String s0, String s1){
//            String result = "";
        if (s0.length()>s1.length()){
            String tmp = s0;
            s0 = s1;
            s1 = tmp;
        }
//            for (int i=0;i<s0.toCharArray().length;i++){
//                if (s1.charAt(i)==s0.charAt(i)){
//                    result += String.valueOf(s0.charAt(i));
//                }else{
//                    break;
//                }
//            }
        int i=0;
        for (;i<s0.toCharArray().length;i++){
            if (s0.charAt(i)!=s1.charAt(i)){
                break;
            }
        }
        return s0.substring(0, i);

    }


    public String longestCommonPrefix(String[] strs) {
        if (strs.length==0) return "";
        if (strs.length==1) return strs[0];
        if (strs.length==2){
            return getCommonPrefix(strs[0], strs[1]);
        }else{
            String[] strs2 = new String[strs.length-1];
            strs[1] = getCommonPrefix(strs[0], strs[1]);

            System.arraycopy(strs, 1, strs2, 0, strs2.length);
            return longestCommonPrefix(strs2);
        }
    }

    public static void main(String[] args) {
        String[][] nums = new String[][]{{"flower","flow","flight"}
                , {"dog","racecar","car"}, {"aca","cba"}, {}, {""}, {"caa","","a","acb"}};
        for (String[] num : nums){
            long started = System.currentTimeMillis();
            String result = new Solution().longestCommonPrefix(num);
            System.out.println("interval: " + (System.currentTimeMillis() - started));
            System.out.println(result);
        }

    }
}
