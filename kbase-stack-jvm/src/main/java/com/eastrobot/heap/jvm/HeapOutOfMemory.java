/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.jvm;


/**
 * Java 堆内存溢出
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @date 2017年8月17日 上午10:12:22
 * @version 1.0
 */
public class HeapOutOfMemory {

	/**
	 * @VM args:-verbose:gc -Xms20M -Xmx20M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
	 * 如果不指定 -Xmn -Xmn 默认多少？
	 * @author eko.zhan at 2017年8月17日 上午10:14:15
	 * @param args
	 *
	 */
	public static void main(String[] args) {
		String s = "";
		byte[] bytes = new byte[1024*1024];
		while(true){
			s += new String(bytes);
		}
	}
}
