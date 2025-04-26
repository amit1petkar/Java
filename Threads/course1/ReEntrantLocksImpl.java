package Threads.course1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLocksImpl {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void process() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " acquired lock.");
            System.out.println(Thread.currentThread().getName() + " now sleeping.");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Exception caught at process for Thread " + Thread.currentThread().getName() + " " + e.getMessage());
        } finally {
            System.out.println(Thread.currentThread().getName() + " releasing lock.");
            lock.unlock();
        }

    }

    public void process2() {
        try {
            System.out.println(Thread.currentThread().getName() + " will try to acquire lock.");
            boolean gotLock = false;
            while (!gotLock) {
                gotLock = lock.tryLock(100, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + " acquired lock: " + gotLock);
            }

        } catch (Exception e) {
            System.out.println("Exception caught at process2 for Thread " + Thread.currentThread().getName() + " " + e.getMessage());
        } finally {
            System.out.println(Thread.currentThread().getName() + " signals for process4.");
            condition.signal();
            System.out.println(Thread.currentThread().getName() + " releasing lock.");
            lock.unlock(); //this will throw IllegalMonitorStateException if lock is not held by current thread.
        }
    }

    public void process3() {
        try {
            System.out.println(Thread.currentThread().getName() + " started with sleeping.");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " will try to acquire lock interruptibly.");
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " acquired lock.");
            System.out.println(Thread.currentThread().getName() + " now sleeping.");
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Exception caught at process3 for Thread " + Thread.currentThread().getName() + " " + e.getMessage());
        } finally {
            System.out.println(Thread.currentThread().getName() + " releasing lock.");
            lock.unlock(); //this will throw IllegalMonitorStateException if lock is not held by current thread.
        }
    }

    public void process4() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " acquired lock.");
            System.out.println(Thread.currentThread().getName() + " now awaits for process 2.");
            condition.await();
            System.out.println(Thread.currentThread().getName() + " received signal.");
        } catch (Exception e) {
            System.out.println("Exception caught at process4 for Thread " + Thread.currentThread().getName() + " " + e.getMessage());
        } finally {
            System.out.println(Thread.currentThread().getName() + " releasing lock.");
            lock.unlock(); //this will throw IllegalMonitorStateException if lock is not held by current thread.
        }
    }

    public void logger() {
        try {
            while (true) {
                System.out.println("LOGGER -- Lock held by threads: " + lock.hasQueuedThreads());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println("Exception caught at logger for Thread " + Thread.currentThread().getName() + " " + e.getMessage());

        }

    }

    public static void main(String[] args) throws Exception {
        ReEntrantLocksImpl reentrantLocks = new ReEntrantLocksImpl();
        Thread t1 = new Thread(reentrantLocks::process, "Thread1");
        Thread t2 = new Thread(reentrantLocks::process2, "Thread2");
        Thread t3 = new Thread(reentrantLocks::process3, "Thread3");
        Thread t4 = new Thread(reentrantLocks::process4, "Thread4");
        Thread logger = new Thread(reentrantLocks::logger, "LOGGER");
        logger.setDaemon(true);
        logger.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
