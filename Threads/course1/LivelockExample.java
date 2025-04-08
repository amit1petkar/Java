package Threads.course1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LivelockExample {
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();
    private static final int SLEEP_TIME=500;

    public void process1() {

        while (true) {
            System.out.println("Process1 trying for lock1");
            lock1.lock();
            System.out.println("Process1 got lock1");
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Process1 trying for lock2");
            if(lock2.tryLock()) {
                System.out.println("Process1 got lock2");
                lock2.unlock();
                System.out.println("Process1 unlocked lock2");
            } else {
                continue;
            }
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }

    public void process2() {

        while (true) {
            System.out.println("Process2 trying for lock2");
            lock2.lock();
            System.out.println("Process2 got lock2");
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Process2 trying for lock1");
            if(lock1.tryLock()) {
                System.out.println("Process2 got lock1");
                lock1.unlock();
                System.out.println("Process2 unlocked lock1");
            } else {
                continue;
            }
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }

    public static void main(String[] args) {
        LivelockExample example = new LivelockExample();
        Thread t1 = new Thread(example::process1, "process1");
        Thread t2 = new Thread(example::process2, "process2");

        t1.start();
        t2.start();

    }
}
