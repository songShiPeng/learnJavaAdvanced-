package com.threadTest;

import java.util.concurrent.TimeUnit;

/**
 * 产生死锁测试
 * @author 宋世鹏
 *
 */

public class TestDeadLock {
	private String A="a";
	private String B="b";
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		new TestDeadLock().deadLock();
		
	}

	/**
	 * 产生死锁的方法
	 * @throws InterruptedException 
	 */
	public void deadLock() throws InterruptedException{
		Thread t1=new Thread(new Runnable(){
			@Override
			public void run(){
				synchronized(A){
					System.out.println("a1");
					try{
						Thread.sleep(2000);
					}catch(Exception e){
						System.out.println("a2");
					}
					synchronized(B){
						System.out.println("a-b");
						
					}
				}
			}
		});
		Thread t2=new Thread(new Runnable(){
			@Override
			public void run(){
				synchronized(B){
					System.out.println("b1");
					try{
						Thread.sleep(2000);
					}catch(Exception e){
						System.out.println("b2");
					}
					synchronized(A){
						System.out.println("b-a");
						
					}
				}
			}
		});
		t1.start();
		t2.start();
		t1.interrupt();
		TimeUnit.SECONDS.sleep(2);
		
		System.out.println(t1.isInterrupted());
	}
}
