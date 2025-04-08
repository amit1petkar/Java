package Threads.course1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerUsingReentrantLocks {
    private static final long SLEEP_TIME = 100;
    private static final long TOTAL_TIME = 2000;
    private static final int MAX_MESSAGES = 5;
    private final Queue<Integer> queue = new LinkedList<>();
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public void produce() throws InterruptedException {
        try{
            lock.lock();
            while (true) {
                Thread.sleep(SLEEP_TIME);
                if (queue.size() >= MAX_MESSAGES) {
                    condition.await();
                } else {
                    int random = (int) (Math.random() * 10);
                    System.out.println("PRODUCER " + Thread.currentThread().getName() + " produced: " + random);
                    queue.add(random);
                    condition.signal();
                }
            }
        } finally {
            condition.signal();
            lock.unlock();
            Thread.sleep(SLEEP_TIME);
        }

    }

    public void consume() throws InterruptedException {

        try {
            lock.lock();
            Thread.sleep(SLEEP_TIME);
            while (true) {
                if (queue.isEmpty()) {
                    condition.await();
                } else {
                    int consumed = queue.poll();
                    System.out.println("Consumer " + Thread.currentThread().getName() + " consumed: " + consumed);
                    condition.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerUsingReentrantLocks pc = new ProducerConsumerUsingReentrantLocks();
        Thread producer = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.setDaemon(true);
        consumer.setDaemon(true);

        producer.start();
        consumer.start();

        producer.join(TOTAL_TIME);
        consumer.join(TOTAL_TIME);

        System.out.println("UNPROCESSED " + pc.queue);

    }
}
