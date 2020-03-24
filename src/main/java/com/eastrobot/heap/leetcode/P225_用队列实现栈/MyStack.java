/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.P225_用队列实现栈;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan>/a>
 * @version 1.0
 * @since 2020-3-21 13:27
 */
class MyStack {

    Queue<Integer> queue1;
    Queue<Integer> queue2;
    int top;

    /** Initialize your data structure here. */
    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue1.add(x);
        top = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int tmp = top;
        queue1.remove(tmp);
        while (!queue1.isEmpty()){
            int c = queue1.poll();
            queue2.add(c);
            top = c;
        }
        while (!queue2.isEmpty()){
            queue1.add(queue2.poll());
        }
        return tmp;
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        System.out.println(myStack.pop());
        System.out.println(myStack.top());
        System.out.println(myStack.empty());
    }
}


