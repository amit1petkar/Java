package EasyLevel;

/*
Write a function that takes a positive integer and returns the sum of its digits.
 */
public class SumOfDigits {
    public static void main(String[] args) {
        int input = 123, result = 0;

        while(input != 0){
            result += input%10;
            input /= 10;
        }

        System.out.println(result);
    }
}
