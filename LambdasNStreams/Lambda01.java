package LambdasNStreams;

import java.util.*;
import java.util.function.*;
import java.util.stream.IntStream;

public class Lambda01 {
    public static void main(String[] args) {

        //Supplier
        //Only Returns val. No args
        System.out.println("---------SUPPLIER EXAMPLE---------");
        Supplier<Integer> randomInt = () -> (int)(Math.random()*100);
        System.out.println(randomInt.get());

        //Consumer
        //Only takes one arg. Returns nothing
        System.out.println("---------CONSUMER EXAMPLE---------");
        List<Integer> l1 = new ArrayList<>();
        Consumer<Integer> addToList = x -> l1.add(x); //Method Ref: l1::add
        System.out.println("Before::"+l1);
        addToList.accept(10);
        System.out.println("After::"+l1);

        //BiConsumer
        //Takes two args. Returns nothing.
        System.out.println("---------BICONSUMER EXAMPLE---------");
        Map<Integer, Integer> m1 = new HashMap<>();
        BiConsumer<Integer, Integer> addToMap = (x,y) -> m1.put(x, y); //Method Ref: m1::put
        System.out.println("Before::"+m1);
        addToMap.accept(10,20);
        System.out.println("After::"+m1);

        //Predicate
        //Takes an arg. Returns boolean
        System.out.println("---------PREDICATE EXAMPLE---------");
        Predicate<Integer> isEven = x -> x%2==0;
        System.out.println("100 is even:"+isEven.test(100));

        //BiPredicate
        //Takes two args. Returns boolean
        System.out.println("---------BIPREDICATE EXAMPLE---------");
        BiPredicate<Integer, Integer> areEqual = (x,y) -> Objects.equals(x, y); //Method ref: Objects::equals
        System.out.println(areEqual.test(10, 10));

        //Function
        //Takes an arg. Returns val
        System.out.println("---------FUNCTION EXAMPLE---------");
        Function<Integer, Integer> doubler = x -> x*2;
        System.out.println(doubler.apply(10));

        //UnaryOperator
        //Takes an arg. Returns val; both of same types
        System.out.println("---------UNARYOPERATOR EXAMPLE---------");
        UnaryOperator<Integer> doubler2 = x -> x*2;
        System.out.println(doubler2.apply(25));

        //BiFunction
        //Takes two args. Returns val
        System.out.println("---------BIFUNCTION EXAMPLE---------");
        BiFunction<Integer, Integer, Integer> sum = (x,y) -> x + y;
        System.out.println(sum.apply(2, 3));

        //BinaryOperator
        //Variant of BiFunction having 2 args and returns val; all of same type.
        System.out.println("---------BINARYOPERATOR EXAMPLE---------");
        BinaryOperator<Integer> sum2 = (a, b) -> a + b; //Method ref: Integer::sum.
        System.out.println(sum2.apply(4, 5));

        //Chaining Lambdas. Only applicable for consumers, predicates, functions
        //Only same type lambdas can be chained.
        System.out.println("---------CHAINING CONSUMERS EXAMPLE---------");
        l1.clear();
        Consumer<Integer> print = s-> System.out.println("PRINTING::"+s);
        IntStream.rangeClosed(1,10).boxed().limit(10).forEach(addToList.andThen(print));
        System.out.println(l1);

        System.out.println("---------CHAINING PREDICATES EXAMPLE---------");
        Predicate<Integer> isNotZero = x -> x!=0;
        Predicate<Integer> isOdd = x -> x%2!=0;
        IntStream.rangeClosed(1,10).boxed().limit(10).filter(isNotZero.and(isOdd)).forEach(print);

        System.out.println("---------CHAINING FUNCTIONS EXAMPLE---------");
        IntStream.rangeClosed(1,10).boxed().limit(10).map(doubler.andThen(doubler2)).forEach(print);

    }
}