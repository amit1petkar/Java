package DSA.UdemyCourse1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/*
FizzBuzz Test
Write a fizzbuzz function that returns a List<string> with the numbers from 1 to n with the following restrictions:
for multiples of 3 store "Fizz" instead of the number
for multiples of 5 store "Buzz" instead of the number
for numbers which are multiples of both 3 and 5 store "FizzBuzz"
 */
public class FizzBuzz {
    public static void main(String[] args) {
        System.out.println(fizzbuzz(20));
    }

    private static List<String> fizzbuzz(int n) {
        List<String> fizzBuzz = new ArrayList<>(n);

        IntStream.rangeClosed(1, n)
                .mapToObj(FizzBuzz::fizzBuzz2)
                .forEach(fizzBuzz::add);

        return fizzBuzz;
    }

    private static String fizzBuzz2(int n) {
        if(n % 15 == 0) {
            return "FizzBuzz";
        } else if (n % 3 == 0) {
            return "Fizz";
        } else if (n % 5 ==0) {
            return "Buzz";
        } else {
            return String.valueOf(n);
        }
    }
}
