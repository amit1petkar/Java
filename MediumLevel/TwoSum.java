package MediumLevel;

/*
Given an array of integers, return the indices of the two numbers that add up to a specific target.
 */
public class TwoSum {
    public static void main(String[] args) {
        int []a = {-2,-7,-2,-15};
        int target = -9;

        int firstIdx = 0, secondIdx = -1;

        /*
        this logic just prints the first possibility. If you want to print all possibilities, print instead of break.
         */

        outer:
        for(int i=0;i < a.length-1;i++){
            firstIdx = i;
            int search = target-a[i];
            for(int j=i+1;j<a.length;j++){
                if (search == a[j]){
                    secondIdx = j;
                    break outer;
                }
            }
        }

        if(secondIdx != -1){
            System.out.println(firstIdx+" "+secondIdx);
        }
    }
}
