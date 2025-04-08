package EasyLevel;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/*
Write a program that prints the numbers from 1 to 100.
For multiples of three, print "Fizz" instead of the number,
and for multiples of five, print "Buzz".
For numbers that are multiples of both three and five, print "FizzBuzz".
 */
public class FizzBuzz {
    public static void main(String[] args) {

        IntConsumer fizzBuzz = i-> {
          if(i%3 ==0) {
              if(i%5==0) {
                  System.out.println("FizzBuzz");
              } else {
                  System.out.println("Fizz");
              }
          } else if(i%5==0) {
              System.out.println("Buzz");
          } else {
              System.out.println(i);
          }
        };

        IntStream.rangeClosed(1,100).forEach(fizzBuzz);
    }
}
