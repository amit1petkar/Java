package EasyLevel;
/*
Write a function that checks if a given string is a palindrome
 */
public class Palindrome {
    public static void main(String[] args) {
        String input = "MADAM";
        System.out.println("Is "+input+" palindrome?:"+isPalindrome(input));
    }

    private static boolean isPalindrome(String input) {
        /*
        We can also solve this without converting to charArray and using charAt(idx).
        My thought was to avoid calling charAt(idx) every time and instead having fast access to chars (char[idx]).
         */
        char[] chars = input.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length / 2; i++) {
            if (chars[i] != chars[length - 1 - i]) {
                return false;
            }
        }
        return true;
    }
}
