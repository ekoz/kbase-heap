/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.P1002_查找常用字符串;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）
 * 组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，
 * 则需要在最终答案中包含该字符 3 次。
 * https://leetcode-cn.com/problems/find-common-characters/
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-21 15:39
 */
class Solution {
    public List<String> commonChars(String[] arr) {

        int[] counter = new int[26];
        // 统计第1个单词各个字母的词频
        for (char c : arr[0].toCharArray()){
            counter[c - 'a']++;
        }
        for (int i=1;i<arr.length;i++){
            int[] tmpArr = new int[26];
            for (char c : arr[i].toCharArray()){
                tmpArr[c-'a']++;
            }

//                System.out.println();
//                for (int c : tmpArr){
//                    System.out.print(c + ", ");
//                }
//                System.out.println();

            for (int j=0;j<26;j++){
                if (tmpArr[j] < counter[j]){
                    // 字母频次的交集
//                        System.out.println(tmpArr[j]);
                    counter[j] = tmpArr[j];
                }
            }
        }
        for (int i : counter){
            System.out.print(i+", ");
        }
        System.out.println();

        LinkedList<String> resList = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            // counter[0] 记录字母a的出现次数
            while (counter[i]-- > 0) {
                resList.addLast("" + (char)('a' + i));
            }
        }

        return resList;
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"bella","label","roller"};
        List<String> list = new Solution().commonChars(arr);
        for (String s : list){
            System.out.print(s + ", ");
        }

        System.out.println();
        System.out.println('b'-'a');
    }
}


