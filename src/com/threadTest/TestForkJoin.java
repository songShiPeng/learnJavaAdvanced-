package com.threadTest;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 测试fork/join框架
 * @author 14642
 *
 */
public class TestForkJoin extends RecursiveTask<Integer>{
	private static int threshold=10;
	private int end;
	private int start;
	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		ForkJoinPool forkPool =new ForkJoinPool();
		TestForkJoin task=new TestForkJoin(0,100000);
		Thread th=new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ThreadMXBean th=ManagementFactory.getThreadMXBean();
				ThreadInfo[] thInfos=th.dumpAllThreads(false, false);
				for(ThreadInfo thInfo:thInfos){
					System.out.println(thInfo.getThreadName());
				}
			}
			
		});
		th.start();
		Future<Integer> result=forkPool.submit(task);
		System.out.println(result.get());
		
	}
	
	public TestForkJoin(int start,int end){
		super();
		this.end=end;
		this.start=start;
	}
	@Override
	protected Integer compute() {
		// TODO Auto-generated method stub
		int sum=0;
		boolean can=(end-start)<threshold;
		if(can){
			for(int i=start;i<=end;i++)
				sum+=i;
		}
		else{
			int middle=(start+end)/2;
			TestForkJoin leftTask=new TestForkJoin(start,middle);
			TestForkJoin rightTask=new TestForkJoin(middle+1,end);
			leftTask.fork();
			rightTask.fork();
			int leftRes=leftTask.join();
			int rightRes=rightTask.join();
			sum=leftRes+rightRes;
		}
		return sum;
	}
}
