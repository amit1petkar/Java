package LambdasNStreams;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams02 {
    public static void main(String[] args) {

        Consumer<String> println = System.out::println;

        //If a collection is collection of collections; flatMap "flattens" them to elements.
        //flatMap basically takes a function (flatMapper) with arg as collection of type (T)
        // and return the stream of respective (args ie collections') type T.
        println.accept("---------FLATMAP---------");
        List<String> fruits = Arrays.asList("APPLE", "BANANA", "CARROT");
        List<String> animals = Arrays.asList("ALLIGATOR", "BABOON", "CHEETAH");
        List<String> flowers = Arrays.asList("ASPEN", "BELLFLOWER", "COLUMBINE");
        List<String> animalsRev = Arrays.asList("ZEBRA", "YAK", "XERUS");

        List<List<String>> items = Arrays.asList(fruits, animals, flowers);

        Function<List<String>, Stream<String>> flatMapper = Collection::stream;

        items.stream().flatMap(flatMapper).forEach(println);

        println.accept("---------SORT STREAM ELEMENTS---------");
        println.accept("---------SORT STREAM ELEMENTS EXAMPLE 1---------");
        animalsRev.stream()
                .peek(println)
                //sorts in natural order (should be Comparable)
                .sorted()
                .forEach(println);

        println.accept("---------SORT STREAM ELEMENTS EXAMPLE 2---------");
        animals.stream()
                .peek(println)
                .sorted(Comparator.reverseOrder())
                .forEach(println);


        //MATCH n SEARCH
        println.accept("---------MATCH n SEARCH---------");
        println.accept("ONLY EVEN VALUES:"+IntStream.rangeClosed(1, 10).filter(x->x%2==0)
                .allMatch(x-> x%2==0)); //all elements should satisfy predicate
        println.accept("CONTAINS APPLE:"+fruits.stream()
                .anyMatch(fruit-> fruit.equals("APPLE"))); //at least one element satisfy predicate
        println.accept("DOESNT CONTAINS PINEAPPLE:"+fruits.stream()
                .noneMatch(fruit-> fruit.equals("PINEAPPLE"))); //none of the elements satisfy predicate

        println.accept("FIRST ELEMENT OF ANIMALS:"+animals.stream().findFirst().get());
        println.accept("ANY ELEMENT OF ANIMALS:"+animals.stream().findAny().get());

        //NUMERIC REDUCTIONS / AGGREGATIONS
        println.accept("---------NUMERIC REDUCTIONS / AGGREGATIONS---------");
        //NOTE: min, max assumes values as "Comparable"; otherwise expects Comparator (Comparator.comparing(attr)) as arg.
        println.accept("MINIMUM OF VALUES IN STREAM:"+IntStream.rangeClosed(1, 10).min().getAsInt());
        println.accept("MAXIMUM OF VALUES IN STREAM:"+IntStream.rangeClosed(1, 10).max().getAsInt());
        println.accept("SUM OF VALUES IN STREAM:"+IntStream.rangeClosed(1, 10).sum());
        println.accept("AVERAGE OF VALUES IN STREAM:"+IntStream.rangeClosed(1, 10).average().getAsDouble());
        println.accept("COUNT OF VALUES IN STREAM:"+IntStream.rangeClosed(1, 10).count());

        //COLLECTORS
        println.accept("---------COLLECTORS---------");
        println.accept("STREAM OF VALUES TO LIST:"+IntStream.rangeClosed(1, 10)
                .boxed() // Collections expects objects; not primitives
                .collect(Collectors.toList())); //Alternative: IntStream().toList()

        Function<Integer, String> evenOrOdd = x-> x%2==0?"EVEN":"ODD";
        println.accept("STREAM OF VALUES TO MAP:"+IntStream.rangeClosed(1, 10).boxed()
                .collect(Collectors.groupingBy(evenOrOdd)));

        println.accept("STREAM OF VALUES TO MAP EG.2:"+IntStream.rangeClosed(1, 10).boxed()
                .collect(Collectors.groupingBy(evenOrOdd,
                        Collectors.counting()))); //downstream collector to count the elements of group

        println.accept("STREAM OF VALUES TO MAP EG.3:"+IntStream.rangeClosed(1, 10).boxed()
                .collect(Collectors.groupingBy(evenOrOdd,
                        Collectors.mapping( //downstream collector to apply further mapping on grouped elements
                                x-> x%2==0,
                                Collectors.toList()
                                //For other collections. E.g. Collectors.toCollection(TreeSet::new) for sorted collection,
                                //toMap(), toSet() etc.
                        ))));

        println.accept("STREAM OF VALUES TO MAP EG.4:"+IntStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.groupingBy(evenOrOdd,
                        Collectors.mapping(
                                Objects::toString, //converting to string
                                Collectors.joining(", ")
                                //to return string comprising elements delimited by provided string
                        ))));

    }
}
