/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */


public class JoinsApp implements Runnable {

    @Override
    public void run() {
        //if(Thread.currentThread().getName().equals("t2"))
        for(int i=0;i<10000;i++)
        {
            try {
                System.out.println("Running Thread "+Thread.currentThread().getName());
//                Thread.sleep(100);
            } catch (Exception ex) {
                Logger.getLogger(JoinsApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        JoinsApp j = new JoinsApp();
        Thread t1 = new Thread(j,"t1");
        Thread t2 = new Thread(j,"t2");
        Thread t3 = new Thread(j,"t3");
        
        System.out.println("In main.. starting threads now...");
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        
        
        
        t3.join();
        
        System.out.println("Reached end of main...");
    }
    
    
    
}
