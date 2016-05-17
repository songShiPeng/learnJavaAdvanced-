package com.collectiontest;
/**
 * 各种map的测试
 */
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


public class MapTest {
	static Map hash=new HashMap<String,String>(3);
	static CountDownLatch c=new CountDownLatch(3);
	    public static void main(String[] args) throws InterruptedException {  
	        new MapTest();
	        Iterator it=hash.keySet().iterator();
	        Thread t1 = new Thread() {  
	            public void run() {  
	                for (int i = 0; i < 10000; i++) {  
	                    hash.put(new Integer(i), Integer.valueOf(i));  
	                }  
	                System.out.println("t1 over"); 
	                c.countDown();
	            }  
	        };  
	        Thread t2 = new Thread() {  
	            public void run() {  
	                for (int i = 10000; i <20000; i++) {  
	                    hash.put(new Integer(i),Integer.valueOf(i));  
	                }  
	                System.out.println("t2 over");  
	                c.countDown();
	            }  
	        };  
	        Thread t3 = new Thread() {  
	            public void run() {  
	                for (int i = 20000; i < 30000; i++) {  
	                    hash.put(new Integer(i),Integer.valueOf(i));  
	                }  
	                System.out.println("t3 over");  
	                c.countDown();
	            }  
	        };  
	        t1.start();
	        t3.start();
	        t2.start();  
	        c.await();
	       //System.out.println(hash);
	        for (int i = 0; i < 30000; i++) {  
                System.out.println(hash.get(i));  
           } 
	    }  

}
