package Threads.course1;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ExecutorsTester {
    private static final Random random = new Random();
    private static final List<Integer> values = new CopyOnWriteArrayList<>();

    public static void process() {
        System.out.println("Incoming thread:" + Thread.currentThread().getName() + " " + random.nextInt(5));
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " INTERRUPTED!!");
        }
        System.out.println("Outgoing thread:" + Thread.currentThread().getName());
    }

    public static Integer process2() {
        Integer value = random.nextInt(100);
        System.out.println("Incoming thread:" + Thread.currentThread().getName() + " " + value);
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " INTERRUPTED!!");
        }
        System.out.println("Outgoing thread:" + Thread.currentThread().getName());
        return value;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            //execute is applicable only for Runnable. Doesn't return anything
            IntStream.rangeClosed(1, 5).forEach(_ -> executorService.execute(ExecutorsTester::process));
            //try with resources will call executorService.shutdown()
        }

        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            //submit is applicable for Runnable (returns Future whose get will return null) and Callable
            IntStream.rangeClosed(1, 5).forEach(_ -> executorService.submit(ExecutorsTester::process));
        }

        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            IntStream.rangeClosed(1, 5).forEach(_ -> executorService.submit(ExecutorsTester::process));
        }

        try (ExecutorService executorService = new ThreadPoolExecutor(3, 3, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3))) {
            IntStream.rangeClosed(1, 5).forEach(_ -> executorService.submit(ExecutorsTester::process));
        }

        try (ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(3)) {
            //run after delay of 3s
            IntStream.rangeClosed(1, 5).forEach(_ -> scheduledExecutorService.schedule(ExecutorsTester::process, 3, TimeUnit.SECONDS));
            TimeUnit.SECONDS.sleep(10); //introduced delay before its shutdown
        }

        try (ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(3)) {
            //runs with initial delay of 3s and after every 5s
            IntStream.rangeClosed(1, 5).forEach(_ -> scheduledExecutorService.scheduleAtFixedRate(ExecutorsTester::process, 3, 5, TimeUnit.SECONDS));
            TimeUnit.SECONDS.sleep(10);
        }

        try (ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(3)) {
            //runs with initial delay of 3s and delay for subsequent tasks of 5s
            IntStream.rangeClosed(1, 5).forEach(_ -> scheduledExecutorService.scheduleWithFixedDelay(ExecutorsTester::process, 3, 5, TimeUnit.SECONDS));
            TimeUnit.SECONDS.sleep(10);
        }

        ScheduledExecutorService scheduledExecutorService2 = new ScheduledThreadPoolExecutor(2);
        IntStream.rangeClosed(1, 100).forEach(_ -> scheduledExecutorService2.scheduleWithFixedDelay(ExecutorsTester::process, 0, 2, TimeUnit.SECONDS));
        TimeUnit.SECONDS.sleep(10);
        scheduledExecutorService2.shutdown(); //will not take in any more tasks. Will wait for other ongoing tasks to complete.
        System.out.println("SHUTDOWN scheduledExecutorService2."); //This will print after shutdown. After this line, any outputs from ongoing tasks will be printed here.

        //blocks and returns true if all tasks were completed before timeout (5s) and scheduledExecutorService2 is terminated. Otherwise, return false
        if (!scheduledExecutorService2.awaitTermination(1, TimeUnit.SECONDS)) {
            //will shutdown immediately. Any threads due for completion will be interrupted.
            System.out.println("TASKS STILL DUE FOR COMPLETION. SHUTTING DOWN NOW");
            scheduledExecutorService2.shutdownNow();
        } else {
            System.out.println("ALL TASKS ARE NOW COMPLETE");
        }

        //Using Callable
        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            Function<Future<Integer>, Integer> futureValueMapper = integerFuture -> {
                try {
                    return integerFuture.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            };

            Integer maxValue = IntStream.rangeClosed(1, 5).boxed()
                    .map(_ -> executorService.submit(ExecutorsTester::process2)) //executorService.invokeAll(<collection of callables>, will return List<Future<V>>)
                    .map(futureValueMapper)
                    .peek(values::add) //Add to my list and proceed. Can also do toList to get list here right away.
                    .reduce(Integer::max).orElse(0);

            System.out.println("PROCESSED VALUES: " + values);
            System.out.println("MAX VALUE: " + maxValue);

        }
    }
}
