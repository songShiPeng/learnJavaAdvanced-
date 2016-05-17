package com.threadTest;


/**
 *100张票，5个售票口
 * @author 宋世鹏
 */
public class SellMovies{
	public static void main(String [] args){
            Piao p=new Piao();
            ThreadTest t1=new ThreadTest(p);
            ThreadTest t2=new ThreadTest(p);
            ThreadTest t3=new ThreadTest(p);
            ThreadTest t4=new ThreadTest(p);
            ThreadTest t5=new ThreadTest(p);
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
	} 
        
}

class ThreadTest extends Thread{
   Piao p;
   public ThreadTest(Piao p){
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
class Piao{
    private int num=100;
    public synchronized boolean  sell(){
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

