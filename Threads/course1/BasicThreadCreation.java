package Threads.course1;


public class BasicThreadCreation {
    public static void main(String[] args) {
        System.out.println("Main thread started:"+Thread.currentThread().getName());

        //print  no. of cores
        System.out.println("No of cores:"+Runtime.getRuntime().availableProcessors());

        Thread t1 = new BasicThreadCreationThread();
        Thread t11 = new BasicThreadCreationThread();
        Thread t2 = new Thread(new BasicThreadCreationThread2());
        Thread t21 = new Thread(new BasicThreadCreationThread2());

        t1.start();
        t2.start();
        t11.start();
        t21.start();
    }
}

class BasicThreadCreationThread extends Thread {
    public void run() {
        System.out.println("This is a basic thread with extending Thread:"+Thread.currentThread().getName());
    }
}

class BasicThreadCreationThread2 implements Runnable {
    public void run() {
        System.out.println("This is a basic thread with implementing Runnable:"+Thread.currentThread().getName());
    }
}
