package Threads.course1;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerUsingSynchronized {
    private static final long SLEEP_TIME = 100;
    private static final long TOTAL_TIME = 500;
    private static final int MAX_MESSAGES = 5;
    private final Object monitor = new Object();
    private final Queue<Integer> queue = new LinkedList<>();


    public void produce() throws InterruptedException {
        synchronized (monitor) {
            while(true) {
                Thread.sleep(SLEEP_TIME);
                if(queue.size() >= MAX_MESSAGES) {
                    monitor.wait();
                } else {
                    int random = (int)(Math.random()*10);
                    System.out.println("PRODUCER "+Thread.currentThread().getName()+" produced: "+random);
                    queue.add(random);
                    monitor.notify();
                }
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (monitor) {
            Thread.sleep(SLEEP_TIME);
            while(true) {
                if(queue.isEmpty()) {
                    monitor.wait();
                } else {
                    int consumed = queue.poll();
                    System.out.println("Consumer "+Thread.currentThread().getName()+" consumed: "+consumed);
                    monitor.notify();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerUsingSynchronized pc = new ProducerConsumerUsingSynchronized();
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

        System.out.println("UNPROCESSED "+pc.queue);

    }

}
