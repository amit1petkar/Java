package DSA;

import java.util.Arrays;

/*

https://leetcode.com/problems/russian-doll-envelopes/description/

You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.

One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.

Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).

Note: You cannot rotate an envelope.



Example 1:

Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
Example 2:

Input: envelopes = [[1,1],[1,1],[1,1]]
Output: 1


Constraints:

1 <= envelopes.length <= 105
envelopes[i].length == 2
1 <= wi, hi <= 105
 */
public class RussianDollEnvelopes {
    public static void main(String[] args) {

        int[][] envelopes = new int[][]{
                {1, 1}
        };

        System.out.println(maxEnvelopes(envelopes));


    }

    public static int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o2[0] <= o1[0] && o2[1] <= o1[1])
                return 1;
            else
                return -1;
        });

        int counter = 1;

        int[] least = envelopes[0];
        for (int z = 1; z < envelopes.length; z++) {
            if (isGreater(envelopes[z], least)) {
                ++counter;
                least = envelopes[z];
            }
        }

        return counter;
    }

    private static boolean isGreater(int[] a, int[] b) {
        if (a[0] > b[0] && a[1] > b[1]) {
            return true;
        } else {
            return false;
        }
    }
}
