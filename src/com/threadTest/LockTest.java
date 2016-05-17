package com.threadTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock的测试
 * @author 14642
 *证明:lock与syn一样，同一时刻只能访问一个带lock的方法
 */

public class LockTest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LockTest().lockStart();
	}
	
	public void lockStart(){
		LockElem e=new LockElem();
		ThreadLock1 r1=new ThreadLock1(e,false);
		ThreadLock1 r2=new ThreadLock1(e,true);
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		t2.start();
		t1.start();
	}
	
	

}
class ThreadLock1 implements Runnable{
	private LockElem e;
	private boolean b;//决定执行get还是set
	public ThreadLock1(LockElem e,boolean b){
		this.e=e;
		this.b=b;
	}
	public void run(){
		if(b)
			e.get();
		else
			e.set();
	}

	
}

class LockElem{
	Lock lock=new ReentrantLock();
	
	public void get(){
		lock.lock();
		try{
			System.out.println("我要跳坑了get");
			TimeUnit.SECONDS.sleep(2);
		}catch(Exception e){
			
		}finally{
			lock.unlock();
		}
	}
	
	public void set(){
		lock.lock();
		try{
			System.out.println("我要跳坑了set");
			TimeUnit.SECONDS.sleep(2);
		}catch(Exception e){
			
		}finally{
			lock.unlock();
		}
	}
}
