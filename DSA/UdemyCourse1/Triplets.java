package DSA.UdemyCourse1;

import java.util.*;

/*
Triplets
Given an array containing N integers, and a number S denoting a target sum.
Find all distinct integers that can add up to form target sum. The numbers in each triplet should be ordered in ascending order, and triplets should be ordered too.
Return empty array if no such triplet exists.

Input
array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 15]
target = 18
Output
[[1, 2, 15], [1, 8, 9], [2, 7, 9], [3, 6, 9], [3, 7, 8], [4, 5, 9], [4, 6, 8], [5, 6, 7]]
 */
public class Triplets {
    public static void main(String[] args) {

        //Assumption: For this problem, it helps the array to be sorted.
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 15};
        int target = 18;

//        System.out.println(bruteForce(input, target).orElse(null));
        //[[1, 2, 15], [1, 8, 9], [2, 7, 9], [3, 6, 9], [3, 7, 8], [4, 5, 9], [4, 6, 8], [5, 6, 7]]
        System.out.println(useTwoPointers_best(input, target).orElse(null));
    }

    //Inspired by Pairs.twoPointers_3rdBest as it gives best time complexity; provided the array is sorted.
    //Time complexity = O(n) (iterate) * O(n) (using Pairs.twoPointers_3rdBest logic) approxs. to O(n^2)
    //Space complexity = O(1)
    //Note: I cannot call Pairs.twoPointers_3rdBest as it returns only one pair. There could be many possibilities.
    private static Optional<List<ArrayList<Integer>>> useTwoPointers_best(int[] arr, int target) {
        List<ArrayList<Integer>> results = new ArrayList<>();

          //Using Pairs.twoPointers_3rdBest
//        for (int i = 0; i < arr.length - 3; i++) {
//            int residual = target - arr[i];
//            int finalI = i;
//            Pairs.twoPointers_3rdBest(Arrays.copyOfRange(arr, i+1, arr.length), residual)
//                    .ifPresent(x->results.add(new ArrayList<>(Arrays.asList(arr[finalI], x[0], x[1]))));
//        }

        for (int i = 0; i < arr.length - 3; i++) {
            int j = i + 1;
            int k = arr.length - 1;

            while(j<k) {
                int actualSum = arr[i] + arr[j] + arr[k];

                if(actualSum == target) {
                    results.add(new ArrayList<>(Arrays.asList(arr[i], arr[j], arr[k])));
                    j++;
                    k--;
                } else if(actualSum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }

        return results.isEmpty() ? Optional.empty() : Optional.of(results);
    }


    //Time complexity= O(n^2) (two ptrs iteration) *O(log(n)) (binary search) approxs. to O(n^2)
    //Worse than this would be O(n^3) where you'd have three pointers : i=0, j=i+1, k=j+1.
    private static Optional<List<ArrayList<Integer>>> bruteForce(int[] arr, int target) {
        List<ArrayList<Integer>> results = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int res = Arrays.binarySearch(arr, j + 1, arr.length, target - arr[i] - arr[j]);
                if (res > 0) {
                    results.add(new ArrayList<>(Arrays.asList(arr[i], arr[j], arr[res])));
                }
            }

        }
        return results.isEmpty() ? Optional.empty() : Optional.of(results);
    }
}
