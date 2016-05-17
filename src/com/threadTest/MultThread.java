package com.threadTest;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;


/**
 * 获得当前线程信息
 * @author java并发编程的艺术
 *
 */

public class MultThread {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadMXBean th=ManagementFactory.getThreadMXBean();
		ThreadInfo[] thInfos=th.dumpAllThreads(false, false);
		for(ThreadInfo thInfo:thInfos){
			System.out.println(thInfo.getThreadName());
		}
	}

}
