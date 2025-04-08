/**
 * https://www.careercup.com/question?id=15882684
 * There is an old dry well. Its sides are made of concrete rings. Each such ring is one meter high, but the rings can have different (internal) diameters. Nevertheless, all the rings are centered on one another. The well is N meters deep; that is, there are N concrete rings inside it.
 * You are about to drop M concrete disks into the well. Each disk is one meter thick, and different disks can have different diameters. Once each disk is dropped, it falls down until: * it hits the bottom of the well; * it hits a ring whose internal diameter is smaller then the disk's diameter; or * it hits a previously dropped disk. (Note that if the internal diameter of a ring and the diameter of a disk are equal, then the disk can fall through the ring.)
 * The disks you are about to drop are ready and you know their diameters, as well as the diameters of all the rings in the well. The question arises: how many of the disks will fit into the well?
 * Write a function:
 * class Solution { int falling_disks(int[] A,int[] B); }
 * that, given two zero-indexed arrays of integers − A, containing the internal diameters of the N rings (in top-down order), and B, containing the diameters of the M disks (in the order they are to be dropped) − returns the number of disks that will fit into the well.
 * For example, given the following two arrays:
 * A[0] = 5 B[0] = 2
 * A[1] = 6 B[1] = 3
 * A[2] = 4 B[2] = 5
 * A[3] = 3 B[3] = 2
 * A[4] = 6 B[4] = 4
 * A[5] = 2
 * A[6] = 3
 * the function should return 4, as all but the last of the disks will fit into the well. The figure shows the situation after dropping four disks.
 * <p>
 * Assume that:
 * N is an integer within the range [1..200,000];
 * M is an integer within the range [1..200,000];
 * each element of array A is an integer within the range [1..1,000,000,000];
 * each element of array B is an integer within the range [1..1,000,000,000].
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
package DSA;

public class Falling_Disks {

    public static void main(String[] args) {
        int well[] = new int[]{5, 6, 4, 3, 6, 2, 3};
        int discs[] = new int[]{2, 3, 5, 2, 4};

        System.out.println(countDiscsInWell(well, discs));
    }

    private static int countDiscsInWell(int[] well, int[] discs) {
        int discsPassed = 0;
        int wellDepth = well.length;
        boolean flag = false, wasStuck = false;
        for (int d = 0; d < discs.length && wellDepth != 1; d++) {
            //go thru well and update their widths as we pass through.
            wasStuck = false;
            for (int i = 0; i < wellDepth; i++) {
                //if disc passes
                if (discs[d] <= well[i]) {
                    //well parsing should happen only once. use flag.
                    if (!flag) {
                        //for first ring just ignore
                        if (i == 0) {
                            continue;
                        } else {
                            //for subsequent rings, if ith ring > (i-1)th ring, reduce (i-1)th ring to that of ith ring
                            if (well[i] > well[i - 1]) {
                                well[i] = well[i - 1];
                            }
                        }
                    }
                } else {
                    //disc stuck at (i-1)th ring
                    wellDepth = i;
                    wasStuck = true;
                    break;
                }
            }
            //well parsed.
            flag = true;
            if (!wasStuck) {
                wellDepth--; //disc passed thru
            }
            discsPassed++;
        }
        return discsPassed;
    }
}
