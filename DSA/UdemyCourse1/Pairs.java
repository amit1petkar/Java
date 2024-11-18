package DSA.UdemyCourse1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/*
Given an array containing N integers, and a number S denoting a target sum.
Find two distinct integers that can pair up to form target sum. Let us assume there will be only one such pair.
Input array = [10, 5, 2, 3, -6, 9, 11]
S=4
Output [10, -6]
 */
public class Pairs {
    public static void main(String[] args) {
        int[] input = {10, 5, 2, 3, -6, 9, 11};
        int target = -3;
//        System.out.println(Arrays.toString(bruteForce(input, target).orElse(null)));
//        System.out.println(Arrays.toString(sortNSearch_2ndBest(input, target).orElse(null)));
//        System.out.println(Arrays.toString(twoPointers_3rdBest(input, target).orElse(null)));
        System.out.println(Arrays.toString(memoizeNSearch_best(input, target).orElse(null)));
    }

    //Time complexity O(n).
    //Space complexity: O(n). (increased mem; because of set.)
    //Note: Checking if val in set/map is O(1).
    public static Optional<int[]> memoizeNSearch_best(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();

        for (int j : arr) {
            //search each element's complement in set, if memoized.
            if (set.contains(target - j)) {
                return Optional.of(new int[]{j, target - j});
            }
            //else memoize
            set.add(j);
        }

        return Optional.empty();
    }

    //Time complexity O(n) (iterate across array) + O(nLog(n)) (sorting) approxs. to O(nLog(n)).
    //Note: If already sorted, then O(n)
    //Space complexity: O(1). (using 2 pointers; in mem.)
    public static Optional<int[]> twoPointers_3rdBest(int[] arr, int target) {

        Arrays.sort(arr);

        for (int i = 0, j = arr.length - 1; i < j; ) {
            int actualTgt = arr[i] + arr[j];
            if (actualTgt == target) {
                return Optional.of(new int[]{arr[i], arr[j]});
            } else if (actualTgt > target) {
                j--;
            } else {
                i++;
            }
        }
        return Optional.empty();
    }

    //Time complexity= O(n) (iteration) * O(log(n)) (binary search) + O(nLog(n)) (sort) approxs. to O(nLog(n))
    //Space complexity: O(1). (in memory)
    public static Optional<int[]> sortNSearch_2ndBest(int[] arr, int target) {

        //sort
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            //search each element's complement
            int res = Arrays.binarySearch(arr, i + 1, arr.length, target - arr[i]);
            if (res > i) {
                return Optional.of(new int[]{arr[i], arr[res]});
            }
        }

        return Optional.empty();
    }


    //Time complexity O(n^2).
    //Space complexity: O(1). (in memory)
    public static Optional<int[]> bruteForce(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return Optional.of(new int[]{arr[i], arr[j]});
                }
            }
        }

        return Optional.empty();
    }
}
