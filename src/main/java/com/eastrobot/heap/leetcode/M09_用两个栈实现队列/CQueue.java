/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.M09_用两个栈实现队列;

import java.util.Stack;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 * (若队列中没有元素，deleteHead 操作返回 -1 )
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-21 14:07
 */
public class CQueue {

    Stack<Integer> stack1;
    Stack<Integer> stack2;
    int size;

    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        size = 0;
    }

    public void appendTail(int value) {
        size++;
        stack1.push(value);
    }

    public int deleteHead() {
        if (size==0) return -1;
        size--;
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
        int t = stack2.pop();
        while (!stack2.empty()){
            stack1.push(stack2.pop());
        }
        return t;
    }

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        cQueue.appendTail(3);
        cQueue.appendTail(4);
        cQueue.appendTail(5);
        cQueue.appendTail(6);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());

    }
}


