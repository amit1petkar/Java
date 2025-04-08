/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Executors0001 implements Runnable {
    private int id;

    public Executors0001(int id) {
        this.id = id;
    }
    
    
    
    public void run()
    {
        System.out.println("Starting:"+Thread.currentThread().getName()+" ID:"+id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Executors0001.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Completed:"+Thread.currentThread().getName()+" ID:"+id);
    }
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        for(int i =0; i<5; i++)
        {
            executor.submit(new Executors0001(i));
        }
        
        executor.shutdown();
//        executor.shutdownNow(); will throw InterruptedException
        
//        executor.submit(new Executors0001(100)); //throws runtime RejectedExecutionException
        System.out.println("All tasks submitted..");
        try {
            //waiting for completion
            executor.awaitTermination(10, TimeUnit.SECONDS); //waits/halts (kind of join) for specified time for all tasks to get completed by threads defined in executor
        } catch (InterruptedException ex) {
            System.out.println("TIMED OUT!!!");
        }
        
        System.out.println("All tasks completed....");
    }
}
