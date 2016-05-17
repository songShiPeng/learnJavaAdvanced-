package com.threadTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 中断测试
 * @author 14642
 *
 */
public class InterruptTest {
	InterruptElem A,B;
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		new InterruptTest().interruptRun();
	}
	
	public void interruptRun() throws InterruptedException{
//		InterruptElem ie1=new InterruptElem();
//		InterruptThread1 i1=new InterruptThread1(ie1,true);
//		Thread t1=new Thread(i1);
//		t1.start();
//		t1.interrupt();
//		//System.out.println(t1.interrupted());不会起作用
//		System.out.println(t1.isInterrupted());
		
		new InterruptTest().synInter();
		//new InterruptTest().lockInter();
	}
	
	/**
	 * synchronized中断有什么效果
	 * @throws InterruptedException
	 */
	public void synInter() throws InterruptedException{
		A=new InterruptElem();
		B=new InterruptElem();
		Thread t1=new Thread(new Runnable(){
			@Override
			public void run(){
				synchronized(A){
					System.out.println("sy-a1");
					try{
						Thread.sleep(2000);
					}catch(Exception e){
						System.out.println("sy-a2");
					}
					synchronized(B){
						System.out.println("sy-a-b");
						
					}
				}
			}
		});
		Thread t2=new Thread(new Runnable(){
			@Override
			public void run(){
				synchronized(B){
					System.out.println("sy-b1");
					try{
						Thread.sleep(2000);
					}catch(Exception e){
						System.out.println("sy-b2");
					}
					synchronized(A){
						System.out.println("sy-b-a");
						
					}
				}
			}
		});
		t1.start();
		t2.start();
		
		TimeUnit.SECONDS.sleep(2);
		t1.interrupt();
		System.out.println(t1.isInterrupted());
	}
	
	/**
	 * lock中断有什么效果
	 * @throws InterruptedException
	 */
	public void lockInter() throws InterruptedException{
		A=new InterruptElem();
		Thread t2=new Thread(new InterruptThread1(A,true));
		Thread t1=new Thread(new InterruptThread1(A,false));
		t1.start();
		t2.start();
		TimeUnit.SECONDS.sleep(1);
		if(t1.getState()==Thread.State.WAITING){
			System.out.println("t1在等待");
			t1.interrupt();
		}
		else if(t2.getState()==Thread.State.WAITING)
		{
			System.out.println("t2在等待");
			t2.interrupt();
		}
		//System.out.println(t1.isInterrupted());
	}

}

class InterruptThread1 implements Runnable {
	private InterruptElem e;
	private boolean b;//决定执行get还是set
	public InterruptThread1(InterruptElem e,boolean b){
		this.e=e;
		this.b=b;
	}
	public void run() {
		if(b){
			try {
				e.set();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				System.out.println("t2放弃");
			}
		} else
			try {
				e.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("t1放弃");
			}
	}

	
}

class InterruptElem{
	Lock lock=new ReentrantLock();
	
	public void get() throws InterruptedException{
		lock.lockInterruptibly();
		try{
			System.out.println("t1已得到锁");
			System.out.println("t1要睡10秒");
			Thread.sleep(10000);
			}catch(Exception e){
			System.out.println("t1得到异常");
			}finally{
				lock.unlock();
			}	
	}
		
	
	
	public void set() throws InterruptedException{
		lock.lockInterruptibly();
		try{
			System.out.println("t2已得到锁");
			System.out.println("t2要睡眠10秒");
			Thread.sleep(10000);
		}catch(Exception e){
			System.out.println("t2异常");
		}finally{
			lock.unlock();
		}	
	}
}
