/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

public class DaemonTest {

    protected int Bx = 100;
    public static void main(String[] args) {
        new WorkerThread().start();
        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {}
        System.out.println("Main Thread ending") ;
    }

}
class WorkerThread extends Thread {

    public WorkerThread() {
        setDaemon(true) ;   // When false, (i.e. when it's a user thread),
                // the Worker thread continues to run.
                // When true, (i.e. when it's a daemon thread),
                // the Worker thread terminates when the main 
                // thread terminates.
    }

    public void run() {
        int count=0 ;
        while (true) {
            System.out.println("Hello from Worker "+count++) ;
            try {
                sleep(5000);
            } catch (InterruptedException e) {}
        }
    }
}