package DSA.UdemyCourse1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Given an array containing N integers, find length of longest band.
A band is defined as a subsequence which can be reordered in such a manner that
all elements appear consecutive (ie with absolute difference of 1 between neighbouring elements).
The longest band is the band (subsequence) which contains maximum integers.

Input Array
[1, 9, 3, 0, 18, 5, 2, 4, 10, 7, 12, 6]
Output
8
Explanation
Possible bands: (Note: diff of 1)
{12},{18} //well, its invalid in my opinion since a band should be at least of size 2.
{9,10}
Largest {0,1,2,3,4,5,6,7}
 */
public class LongestBand {
    public static void main(String[] args) {
        int[] arr = {1, 9, 3, 0, 18, 5, 2, 4, 10, 7, 12, 6};
//        int[] arr = {0,2};
        System.out.println(tutorSol_longestBand(arr));
    }

    //Better solution
    //Time Complexity: O(n) (iterate) + O(n) (looking up other elements and count) approxs to O(n)
    //Space Complexity: O(n) (using hash set)
    public static int tutorSol_longestBand(int[] arr) {
        Set<Integer> lookup = new HashSet<>();
        Arrays.stream(arr).forEach(lookup::add);
        int max = 0, count;

        //search for each element if it can be start of band.
        for (int j : arr) {
            if (!lookup.contains(j - 1)) {
                count = 1;
                int temp = j;
                while (lookup.contains(++temp)) {
                    count++;
                }
                max = Math.max(max, count);
            }
        }
        return max;
}

//sort and get the longest band
//Time Complexity: O(nLog(n)) (sorting) + O(n) (iterate) approxs to O(nLog(n))
//Space Complexity: O(1)
public static int mySol_longestBand(int[] arr) {
    Arrays.sort(arr);
//        int[] longest = null;

    if (arr.length < 2) return arr.length;

    int max = 0, count = 1;
//        int start = 0;
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] - arr[i - 1] == 1) {
            count++;
        } else {
            if (count > max) {
                max = count;
//                    System.out.println(Arrays.toString(Arrays.copyOfRange(arr, start, i)));
//                    longest = Arrays.copyOfRange(arr, start, i);
            }
            count = 1;
//                start = i;
        }
    }

//        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, start, arr.length)));

    return Math.max(count, max);
}
}
