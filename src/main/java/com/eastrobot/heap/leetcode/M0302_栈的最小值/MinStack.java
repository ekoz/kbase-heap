/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.M0302_栈的最小值;

import java.util.Stack;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/15 16:07
 */
public class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()){
            minStack.add(x);
        }else{
            minStack.push(Math.min(x, minStack.peek()));
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        // -3
        System.out.println(minStack.getMin());
        minStack.pop();
        //      --> 返回 0.
        System.out.println(minStack.top());
        //   --> 返回 -2.
        System.out.println(minStack.getMin());
    }
}


