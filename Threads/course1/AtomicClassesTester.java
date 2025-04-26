package Threads.course1;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClassesTester {
    private final static Integer loopCount = 300;
    private Integer counter = 0;
    private AtomicInteger atomicIntegerCounter = new AtomicInteger(0);

    private void incrementCounter1() {
        for (int i = 0; i < loopCount; i++) {
            atomicIntegerCounter.incrementAndGet();
            ++counter;
        }
    }

    private void incrementCounter2() {
        for (int i = 0; i < loopCount; i++) {
            atomicIntegerCounter.incrementAndGet();
            ++counter;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicClassesTester atomicClassesTester = new AtomicClassesTester();
        Thread t1 = new Thread(atomicClassesTester::incrementCounter1);
        Thread t2 = new Thread(atomicClassesTester::incrementCounter2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter:" + atomicClassesTester.counter);
        System.out.println("AtomicCounter:" + atomicClassesTester.atomicIntegerCounter.get());


    }
}
