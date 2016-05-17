package com.threadTest;


/**
 * 测试ThreadLocal
 * @author 宋世鹏
 *
 */

public class TestThreadLocal {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PiaoThreadLocal p=new PiaoThreadLocal();
        ThreadTestThreadLocal t1=new ThreadTestThreadLocal(p);
        ThreadTestThreadLocal t2=new ThreadTestThreadLocal(p);
        ThreadTestThreadLocal t3=new ThreadTestThreadLocal(p);
        ThreadTestThreadLocal t4=new ThreadTestThreadLocal(p);
        ThreadTestThreadLocal t5=new ThreadTestThreadLocal(p);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
	}

}
class ThreadTestThreadLocal extends Thread{
	   PiaoThreadLocal p;
	   public ThreadTestThreadLocal(PiaoThreadLocal p){
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
class PiaoThreadLocal{
    private ThreadLocal<Integer> num=new ThreadLocal<Integer>(){
    	protected Integer initialValue(){
    		return 100;
    	}
    };
//    
//    public PiaoThreadLocal(){
//    	num.set(100);
//    }
    //volatile int num=100;
    public  boolean  sell(){
      if(num.get()>0){
        num.set((num.get())-1);
        System.out.println("剩余"+num.get()+Thread.currentThread().getName());
        return true;
      }
      else{
         return false; 
      }
    }
}
