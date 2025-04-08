/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
class MyThread implements Runnable
{
    public volatile boolean isRunning  = true;
    public void run() 
    {
        while(isRunning)
        {
            try {
                System.out.println("Hello Thread");
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void shutDown()
    {
        isRunning = false;
    }
}


public class VolatileEg001 {
    public static void main(String[] args) {
        MyThread m = new MyThread();
        Thread t1 = new Thread(m);
        
        t1.start();
        System.out.println("Press Return to stop..");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        m.shutDown();
        
    }
    
}
