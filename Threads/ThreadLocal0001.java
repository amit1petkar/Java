/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author admin
 */
public class ThreadLocal0001 implements Runnable {
    
    private static final AtomicInteger uniqueId = new AtomicInteger(0);

     private static final ThreadLocal < Integer > uniqueNum = 
         new ThreadLocal < Integer > () {
             @Override 
             protected Integer initialValue() {
                 System.out.println("ThreadLocal initialValue() run by "+Thread.currentThread().getName());
                 return uniqueId.getAndIncrement();
         }
     };
    
    @Override
    public void run()
    {
        System.out.println("Thread: "+Thread.currentThread().getName()+" value:"+uniqueNum.get());
        try{
        Thread.sleep(3000);
        }catch(Exception ex){}
        System.out.println("After sleep Thread: "+Thread.currentThread().getName()+" value:"+uniqueNum.get());
    }
    
    public static void main(String[] args) throws Exception {
        ThreadLocal0001 l = new ThreadLocal0001();
        Thread t;
        for(int i=0;i<10;i++)
        {
            t = new Thread(l,"T0"+i);
            Thread.sleep(1000);
            t.start();
        }
    }
    
}
