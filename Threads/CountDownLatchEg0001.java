/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author admin
 */
class Processor implements Runnable
{
    private CountDownLatch latch ;

    public Processor(CountDownLatch latch) {
        this.latch=latch;
    }
    
    
    public void run(){
        System.out.println("Started: "+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
        latch.countDown();
        System.out.println("Completed: "+Thread.currentThread().getName());
    }
}

public class CountDownLatchEg0001 {
    
    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(3);
        
        for(int i=0;i<4;i++)
        {
            ex.submit(new Processor(latch));
        }
        
        ex.shutdown();
        try {
            latch.await(1, TimeUnit.DAYS);
        } catch (InterruptedException ex1) {
            System.out.println("AWAITING INTERRUPTED");
//            Logger.getLogger(CountDownLatchEg0001.class.getName()).log(Level.SEVERE, null, ex1);
        }
        
        System.out.println("In main.. Completed..");
    }
    
}
