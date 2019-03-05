/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.demo;
/**
 * Java栈溢出
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @date 2017年8月17日 上午10:24:49
 * @version 1.0
 */
public class StackOverflow {
	
	private int i ;
	public void plus() {
		i++;
		plus();
	}

	/**
	 * 
	 * @VM args:-verbose:gc -Xms20M -Xmx20M -Xss128k -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
	 * @param args
	 * 
Error:stack length:992
java.lang.StackOverflowError
	at com.eastrobot.StackOverFlow.plus(StackOverFlow.java:15)
	 */
	public static void main(String[] args) {
		StackOverflow stackOverFlow = new StackOverflow();
		try {
			stackOverFlow.plus();
		} catch (Exception e) {
			System.out.println("Exception:stack length:"+stackOverFlow.i);
			e.printStackTrace();
		} catch (Error e) {
			System.out.println("Error:stack length:"+stackOverFlow.i);
			e.printStackTrace();
		}
	}
}