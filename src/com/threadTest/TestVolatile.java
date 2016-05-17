package com.threadTest;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 测试volatile变量
 * @author 宋世鹏
 *
 */

public class TestVolatile {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PiaoNotSec p=new PiaoNotSec();
        ThreadTestNotSec t1=new ThreadTestNotSec(p);
        ThreadTestNotSec t2=new ThreadTestNotSec(p);
        ThreadTestNotSec t3=new ThreadTestNotSec(p);
        ThreadTestNotSec t4=new ThreadTestNotSec(p);
        ThreadTestNotSec t5=new ThreadTestNotSec(p);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
	}

}
class ThreadTestNotSec extends Thread{
	   PiaoNotSec p;
	   public ThreadTestNotSec(PiaoNotSec p){
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
class PiaoNotSec{
	// private int num=100;
    //volatile int num=100;
    public  boolean  sell(){
      if(num>0){
        num--;
        System.out.println("剩余"+num+Thread.currentThread().getName());
        return true;
      }
      else{
         return false; 
      }
    }
}
*/

/**
 * 测试原子类
 * @author 14642
 *
 */
class PiaoNotSec{
	// private int num=100;
    //volatile int num=100;
	private AtomicInteger num=new AtomicInteger(100);
    public  boolean  sell(){
      if(num.get()>0){
        num.getAndDecrement();
        System.out.println("剩余"+num+Thread.currentThread().getName());
        return true;
      }
      else{
         return false; 
      }
    }
}
