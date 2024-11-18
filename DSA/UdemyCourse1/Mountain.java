package DSA.UdemyCourse1;

/*
Write a function that takes input an array of distinct integers, and returns the length of highest mountain.
• A mountain is defined as adjacent integers that are strictly increasing until they reach a peak, at which the become strictly decreasing.
• At least 3 numbers are required to form a mountain.

Input: [5,6,1,2,3,4,5,4,3,2,0,1,2,3,-2,4]
Output: 9 (1,2,3,4,5,4,3,2,0)
increasing +++++++++ -------decreasing

 */
public class Mountain {
    public static void main(String[] args) {

        int[] arr = {5, 6, 1, 2, 3, 4, 5, 4, 3, 2, 0, 1, 2, 3, -2, 4}; //9
//        int[] arr = {3,4,5,6,5}; //5
//        int[] arr = {6, 5, 4, 3, 4}; //0
//        int[] arr = {6,5,4,3}; //0
//        int[] arr = {3,4,5,6}; //0
        System.out.println(tutorsApproach_mountain(arr));

    }

    //Time Complexity: O(n). Space Complexity: O(1) (in mem)
    //Approach:
    // Counting elements on either sides of peak. Peak is the element which is greater than element on LHS and RHS.
    // E.g. 5-6-4. 6 is peak as its greater than 5 and 4
    public static int tutorsApproach_mountain(int[] a) {

        int highest_mountain = 0;

        //i =1 and <=a.length-2 to indicate that first and last element can never be peak
        for (int i = 1; i <= a.length - 2; ) {

            //identify peak
            if (a[i] > a[i - 1] && a[i] > a[i + 1]) {
                int mountain = 1; //peak

                //count LHS
                int j = i;
                while (j >= 1 && a[j] > a[j - 1]) {
                    j--;
                    mountain++;
                }

                //count RHS
                //note: we are iterating and updating index i coz it serves two purposes:
                // 1. since RHS is trending down, it would never be part of peak.
                // 2. "i" continues to increment, no need to have another placeholder.
                while (i <= a.length - 2 && a[i] > a[i + 1]) {
                    i++;
                    mountain++;
                }

                highest_mountain = Math.max(mountain, highest_mountain);
            } else {
                i++;
            }
        }

        return highest_mountain;

    }

    //Time Complexity: O(n). Space Complexity: O(1) (in mem)
    //Wrong.. Needs some correction.
    //Approach: Too complex I guess :(
    //At each iteration, I check if the trend is up OR down.
    //To make a mountain, I expect at least one up (peak).
    //Keeping track of up and down for different use cases makes the solution more tricky.
    public static int myApproach_mountain(int[] a) {
        int highest_mountain = 0, mountain_count = 2;
        boolean down = false, up = false;

        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1]) {
                if (!up) {
//                    mountain_count = i==1?2:1;
                    up = true;
                    down = false;
                }
                if (down) {
                    highest_mountain = Math.max(mountain_count, highest_mountain);
                    down = false;
                    up = false;
                    mountain_count = 1;
                }
                mountain_count++;
            } else {
                if (!down) down = true;
                mountain_count++;
            }
        }

//        if (down) {
//            highest_mountain = Math.max(mountain_count, highest_mountain);
//        }

        return highest_mountain < 3 ? 0 : highest_mountain;
    }
}
