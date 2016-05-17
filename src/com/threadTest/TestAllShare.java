package com.threadTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试volatile，原子类
 * @author 14642
 *
 */
public class TestAllShare {
	static final int TARGET_NUM=5000;
	int num=0;
	volatile int num1=0;
	AtomicInteger num2=new AtomicInteger(0);
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		new TestAllShare().testIncrement();
	}
	public void  testIncrement() throws InterruptedException{
		Thread t1=new Thread(new AllTest());
		Thread t2=new Thread(new AllTest());
		Thread t3=new Thread(new AllTest());
		Thread t4=new Thread(new AllTest());
		Thread t5=new Thread(new AllTest());
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		System.out.println("int--"+num);
		System.out.println("volatile--"+num1);
		System.out.println("ato--"+num2.get());
	}
	class AllTest implements Runnable{
		public void run(){
			for(int i=0;i<TARGET_NUM;i++){
				num++;
			}
			for(int i=0;i<TARGET_NUM;i++){
				num1++;
			}

			for(int i=0;i<TARGET_NUM;i++){
				num2.getAndIncrement();
			}
		}
	}

}
