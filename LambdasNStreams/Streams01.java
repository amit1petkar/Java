package LambdasNStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams01 {
    public static void main(String[] args) {
        //Stream of range of integers
        System.out.println("---------RANGE CLOSED STREAM---------");
        List<Integer> l1 = new ArrayList<>();
        IntStream.rangeClosed(1,20) //int values from 1 to 20
                .boxed() //Integer values
                .forEach(l1::add);
        System.out.println(l1);

        //stream from collections
        System.out.println("---------COLLECTIONS STREAM---------");
        System.out.println(l1.stream()
                .reduce(Integer::sum).get()); //reduce without identity value returns Optional.
                                              // Better: .reduce(0, Integer::sum)

        //Stream of hardcoded values
        System.out.println("---------STREAM OF HARDCODED VALUES---------");
        System.out.println(Stream.of(1,2,3,4,5,6,7,8,9,10).reduce(Integer::sum).get());

        //Infinite Stream of values
        System.out.println("---------STREAM OF INFINITE VALUES---------");
        Consumer<Integer> printInt = i -> System.out.println("PRINTING:"+i);
        IntSupplier randomInt = ()-> (int)(Math.random()*100);
        IntStream.generate(randomInt)
                .boxed()
                .limit(10) //limits only 10 from infinite values
                .forEach(printInt);

        //Using Map (takes function), Filter (takes predicate), Reduce (takes BiFunction)
        System.out.println("---------MAP FILTER REDUCE EXAMPLE---------");

        Predicate<Integer> isEven = x -> x%2==0;
        Function<Integer, Integer> printAndDouble = x -> {
            System.out.println(x);
            return x*2;
        };
        Function<Integer, Integer> doubler = x-> x*2;
        Function<Integer, Integer> tripler = x-> x*3;
        BinaryOperator<Integer> sum = Integer::sum;
        Consumer<Integer> print = System.out::println;

        System.out.println("---------MAP FILTER REDUCE EXAMPLE WITHOUT PEEK---------");
        Integer i = IntStream.generate(randomInt)
                .boxed()
                .limit(10)
                .filter(Predicate.not(isEven)) //FILTER ODD NOS
                //MAP TO PRINT ODD NOS AND DOUBLE THEM.
                // NOTE: to print value, I had to tweak doubler (now printAndDouble) function to print this.
                // Better solution is using peek(Consumer). See next example
                .map(printAndDouble)
                .reduce(sum).get(); //REDUCE TO SUM OF VALS
        System.out.println("RESULT:"+i);

        System.out.println("---------MAP FILTER REDUCE EXAMPLE WITH PEEK---------");
        Integer j = IntStream.generate(randomInt)
                .boxed()
                .limit(10)
                .skip(1) //SKIP 1 ELEMENT. SO 9 ELEMENTS TO PROCESS
                .distinct() //REMOVES DUPLICATES
                .filter(isEven) //FILTER TO EVEN NOS
                .peek(print) //PRINT. PEEK TAKES CONSUMER and it's an intermediary operation.
                .map(tripler.compose(doubler)) //SAME AS doubler.andThen(tripler)
                //.map(Function.identity()) //REDUNDANT CALL. RETURNS THE VALUE AS IS

                //REDUCE TO 10 + SUM OF VALS.
                // NOTE: when identity value is provided (10), "reduce"'s return val is not Optional
                .reduce(10, sum);
        System.out.println("RESULT:"+j);

    }
}
