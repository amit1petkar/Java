/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

/**
 *
 * @author admin
 */
class MyThread001 extends Thread
{
    @Override
    public void run()
    {
        System.out.println("Inside MyThread:"+Thread.currentThread().getName());  
    }
}


class MyThread002 implements Runnable
{

    @Override
    public void run() {
        
        System.out.println("Inside MyThread002 before sleep:"+Thread.currentThread().getName());
        try{
        Thread.sleep(11000);
        }catch(InterruptedException ex){
            System.out.println("Interrupted"); return;}
        System.out.println("Inside MyThread002 after sleep");
    }
    
}

public class Thread001 {
    
    public static void main(String[] args) throws InterruptedException {
/*        
        //MyThread001 t = new MyThread001();
        Thread t = new Thread(new MyThread001());
        
        //t.setName("Hello");
        t.start();
        
        //Thread t1 = new Thread(new MyThread002());
        Thread t1 = new Thread(new MyThread002(),"Hello Again");
        t1.start();
*/
        Thread t = new Thread(new MyThread002());
        System.out.println("Main thread..");
        t.start();
        System.out.println("In Main thread.. before sleep");
        Thread.sleep(1000);
        System.out.println("In Main thread.. after sleep");
        System.out.println("In Main thread..before join");
        t.join(5000);
        //t.interrupt();
        System.out.println("In Main thread..after join");
        
        t.run();
        
    }
    
}
