package com.threadTest;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger的使用
 * @author java并发编程的艺术
 *
 */
public class TestExchanger {
	private static final Exchanger<String> ex=new Exchanger<String>();
	private static ExecutorService thPool=Executors.newFixedThreadPool(2);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		thPool.execute(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String A="我是AAAAA";
				try {
					
					String B=ex.exchange(A);
					System.out.println("我在A这，我要输出B了");
					System.out.println(B);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		thPool.execute(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String B="我是BBBBBB";
				try {
					String A=ex.exchange(B);
					System.out.println("我在B这，我要输出A了");
					System.out.println(A);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		thPool.shutdown();
		
	}

}
