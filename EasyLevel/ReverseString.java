package EasyLevel;

/*
Write a function that reverses a given string.
 */
public class ReverseString {
    public static void main(String[] args) {
        String input = "STRESSED";

        char[] chars = input.toCharArray();

        for(int i=0, j=chars.length-1; i<j; i++, j--){
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        System.out.println(String.valueOf(chars));
    }
}
