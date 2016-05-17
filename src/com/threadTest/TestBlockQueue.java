package com.threadTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 测试阻塞队列的使用
 * @author 宋世鹏
 *
 */
public class TestBlockQueue {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ArrayBlockingQueue<Integer> arrayTest=new ArrayBlockingQueue<Integer>(10);
		arrayTest.offer(2, 10, TimeUnit.SECONDS);
		System.out.println(arrayTest.remove());
	}

}
