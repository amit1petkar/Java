package Threads.course1;

import java.util.concurrent.Semaphore;

public class SemaphoreTester {
    private static int permitCount = 5;
    private Semaphore semaphore = new Semaphore(permitCount);

    private void consumer() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ": acquiring permit");
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ": acquired permit");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void produce() {
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ": checking for permits");
                if (semaphore.availablePermits() == 0) {
                    System.out.println(Thread.currentThread().getName() + ": releasing permits");
                    semaphore.release(permitCount);
                }
//                System.out.println(Thread.currentThread().getName() + ": " + semaphore.availablePermits() + " permits available");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void logger() {
        try {
            while (true) {
                System.out.println("LOGGER-> Semaphore permits available:" + semaphore.availablePermits());
                Thread.sleep(500);
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreTester tester = new SemaphoreTester();
        Thread t1 = new Thread(tester::consumer, "consumer");
        Thread t2 = new Thread(tester::produce, "producer");
        Thread logger = new Thread(tester::logger, "logger");
        t1.setDaemon(true);
        t2.setDaemon(true);
        logger.setDaemon(true);

        t1.start();
        t2.start();
//        logger.start();

        t1.join(10000);
        t2.join(10000);

    }
}
