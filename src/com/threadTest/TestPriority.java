package com.threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 线程优先级的使用
 * @author java并发编程的艺术摘抄
 *
 */

public class TestPriority {
	private static volatile boolean notStart=true;
	private static volatile boolean notEnd=true;
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		List<Job> jobs=new ArrayList<Job>();
		for(int i=0;i<10;i++){
			int priority=i<5?1:10;
			Job job=new Job(priority);
			Thread thread=new Thread(job,"Thread"+i);
			thread.setPriority(priority);
			thread.start();
			jobs.add(job);
		}
		notStart=false;
		TimeUnit.SECONDS.sleep(10);
		notEnd=false;
		for(Job job:jobs){
			System.out.println(job.priority+"--"+job.jobCount);
		}
	}
	
	static class Job implements Runnable{
		private int priority;
		private long jobCount;
		
		public Job(int priority){
			this.priority=priority;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(notStart){
				Thread.yield();
			}
			while(notEnd){
				Thread.yield();
				jobCount++;
			}
		}
		
	}

}
