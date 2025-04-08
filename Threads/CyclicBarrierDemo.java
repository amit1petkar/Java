/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author admin
 */
class MyRunnable2 implements Runnable
{
    CyclicBarrier c1, c2;
    MyRunnable2(CyclicBarrier c1, CyclicBarrier c2)
    {
        this.c1 = c1;
        this.c2 = c2;
    }
    
    public void run()
    {
        try
        {
        System.out.println("THREAD AWAITING at BARRIER 1:"+Thread.currentThread().getName());
        c1.await();
//        Thread.sleep(2000);
        System.out.println("THREAD AWAITING at BARRIER 2:"+Thread.currentThread().getName());
        c2.await();
        
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }
        
    }
}

public class CyclicBarrierDemo {
    
    public static void main(String[] args) {
        
        Runnable barrierAction1 = new Runnable(){ 
        public void run()
        {
            System.out.println("BARRIER ACTION 1 executed");
        }
        };
        
        Runnable barrierAction2 = new Runnable(){ 
        public void run()
        {
            System.out.println("BARRIER ACTION 2 executed");
        }
        };
        
        CyclicBarrier c1 = new CyclicBarrier(2,barrierAction1);
        CyclicBarrier c2 = new CyclicBarrier(2,barrierAction2);
        
        MyRunnable2 r1 = new MyRunnable2(c1, c2);
        MyRunnable2 r2 = new MyRunnable2(c1, c2);
        MyRunnable2 r3 = new MyRunnable2(c1, c2);
        
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
    }
    
    
    
}
