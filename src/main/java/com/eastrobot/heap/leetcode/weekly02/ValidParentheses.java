/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.weekly02;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/16 16:53
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class ValidParentheses {

    class Solution {
        public boolean isValid(String s) {
            if (s.length()==0) return true;
            int sum = 0;
            Map<Character, Character> map = new HashMap<Character, Character>(){
                {
                    put('}', '{');
                    put(']', '[');
                    put(')', '(');
                }
            };
            Stack<Character> stack = new Stack<>();
            for (int i=0;i<s.length();i++){
                if (stack.isEmpty()){
                    stack.push(s.charAt(i));
                }else{
                    if (stack.peek().equals(map.get(s.charAt(i)))){
                        stack.pop();
                    }else{
                        stack.push(s.charAt(i));
                    }
                }
            }
            return stack.empty();
        }
    }

    public static void main(String[] args) {
        long started = System.currentTimeMillis();
        String s = "(){}[]";
//        s = "[{()}]";
        s = "[}}[]";
        s = "(]";
        boolean result = new ValidParentheses().new Solution().isValid(s);
        System.out.println("interval: " + (System.currentTimeMillis() - started));
        System.out.println(result);
    }
}
