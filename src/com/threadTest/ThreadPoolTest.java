package com.threadTest;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池的使用
 * @author 宋世鹏
 *
 */
public class ThreadPoolTest {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		// TODO Auto-generated method stub
		ExecutorService p2=Executors.newCachedThreadPool();
		ExecutorService p1=Executors.newFixedThreadPool(4);
		Seller p=new Seller();
		Seller Seller2=new Seller();
		SellThread t1=new SellThread(p);
		SellThread t2=new SellThread(Seller2);
		Future<Integer> result=p1.submit(t1,3);
		p1.submit(t1);
		p1.submit(t1);
		p1.submit(t2);
		p1.submit(t2);
		System.out.println(result.get());
	}

}
class SellThread implements Runnable{
	   Seller p;
	   public SellThread(Seller p){
	       this.p=p;
	   }
	   public void run(){
	       while(true){
	            if(! p.sell())
	                break;
	            try{
	            Thread.sleep(100);
	            }catch(Exception e){
	                
	            }
	       }
	   } 
}
/**
 * 售票
 * @author 
 *
 */
class Seller{
	    private int num=100;
	    public synchronized boolean  sell(){
	      if(num>0){
	        num--;
	        System.out.println("剩余数量--"+num+Thread.currentThread().getName());
	        return true;
	      }
	      else{
	         return false; 
	      }
	    }
	}
