package DSA;

/*
https://leetcode.com/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150

You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
and the last n elements are set to 0 and should be ignored. nums2 has a length of n.



Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
Example 3:

Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.


Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
 */

public class MergeSortedArray {

    // O(m*n)
    public void merge1(int[] nums1, int m, int[] nums2, int n) {

        if(n == 0)
            return;

        if(m == 0) {
            //apparently nums1=nums2 wasn't right
            for(int j=0; j<n; j++) {
                nums1[j] = nums2[j];
            }
            return;
        }

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(nums1[i]>=nums2[j]) {
                    swap(nums1, i, nums2, j);
                }
            }
        }

        for(int j=0; j<n; j++) {
            for(int k=j+1; k<n; k++) {
                if(nums2[j]>=nums2[k]) {
                    swap(nums2, j, nums2, k);
                }
            }
            nums1[m+j] = nums2[j];
        }
    }

    private void swap(int[] nums1, int idx1, int[] nums2, int idx2) {
        //using temp var increases memory
        nums1[idx1] = nums1[idx1] + nums2[idx2];
        nums2[idx2] = nums1[idx1] - nums2[idx2];
        nums1[idx1] = nums1[idx1] - nums2[idx2];
    }

    //O(m+n). Best time for Java. But probably needs better mem usage.
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        if(n == 0)
            return;

        if(m == 0) {
//            System.arraycopy(nums2, 0, nums1, 0, nums2.length);
            for(int j=0; j<n; j++) {
                nums1[j] = nums2[j];
            }
            return;
        }

        int i=0, j=0, mt=m;
        while(i<mt && j<n) {
            if(nums1[i] >= nums2[j]) {
                insert(nums2[j], nums1, mt, i);
                j++;
                mt++;
            }
            i++;
        }

        if(j<=n-1) {
            for(int k=i; k<m+n; k++) {
                nums1[k] = nums2[j++];
            }
        }
    }

    private void insert(int val, int[] arr, int size, int index) {
        for(int i=size; i>index; i--) {
            arr[i] = arr[i-1];
        }
        arr[index] = val;
    }

    //better mem and logic. Start with end of each array (m-1 and n-1) and push the greatest at the end of nums1 (m+n-1).
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0)return;
        int len1 = nums1.length;
        int end_idx = len1-1;
        while(n > 0 && m > 0){
            if(nums2[n-1] >= nums1[m-1]){
                nums1[end_idx] = nums2[n-1];
                n--;
            }else{
                nums1[end_idx] = nums1[m-1];
                m--;
            }
            end_idx--;
        }
        while (n > 0) {
            nums1[end_idx] = nums2[n-1];
            n--;
            end_idx--;
        }
    }

}
