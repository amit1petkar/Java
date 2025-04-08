/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author admin
 */
class MyRunnable implements Runnable
{
    Object o;
    Exchanger ex;
    MyRunnable(Exchanger ex, Object o)
    {
        this.ex =ex;
        this.o=o;
    }
    
    public void run()
    {
        try
        {
            System.out.println("Thread name:"+Thread.currentThread().getName());
            
            System.out.println(Thread.currentThread().getName()+" current object value:"+this.o.toString());
            this.o = this.ex.exchange(this.o);
//            this.o = this.ex.exchange(this.o,3, TimeUnit.SECONDS); If another thread doesnt show up within specified time, it will throw TimeOutException
//            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName()+" exchanged object value:"+this.o.toString());
            
        }catch(Exception ex){ex.printStackTrace();}
    }
}

public class ExchangerDemo {
    public static void main(String[] args) throws Exception {
        Exchanger ex = new Exchanger();
        MyRunnable r1 = new MyRunnable(ex, "A");
        MyRunnable r2 = new MyRunnable(ex, "B");
        MyRunnable r3 = new MyRunnable(ex, "C");
        MyRunnable r4 = new MyRunnable(ex, "D");
        
        new Thread(r1,"Thread 01").start();
        
        new Thread(r2,"Thread 02").start();
        Thread.sleep(4000);
        new Thread(r3,"Thread 03").start();
        new Thread(r4,"Thread 04").start();
    }
    
    
}
