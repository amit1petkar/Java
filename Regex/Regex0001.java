package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex0001 {
    public static void main(String[] args) {

        Pattern p = Pattern.compile("(\\d\\d)\\1\\1");
        Matcher m = p.matcher("232323");

        while(m.find()) {
            System.out.println(m.group());
        }
    }
}
