package DSA;

/*
https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/?envType=study-plan-v2&envId=top-interview-150

Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.



Example 1:

Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]
Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).


Constraints:

1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.
 */
public class RemoveDuplicates2 {

    //my attempt. Doesn't work. Too complex.
    public int removeDuplicates3(int[] nums) {
        int size = nums.length;

        if (size == 1 || size == 2) {
            return size;
        }

        int idx = 0, max = 2;

        for (int i = 1, count = 1; i < size; i++) {
            if (nums[i] == nums[idx]) {
                count++;
                if (count <= max) {
                    idx++;
                }

            } else {
                if (count > max) {
                    int i1 = i, idx1 = idx;
                    while (i1 < size) {
                        nums[idx1] = nums[i1];
                        idx1++;
                        i1++;
                    }
                    while (count != max) {
                        size--;
                        count--;
                    }
                    i = idx;
                } else {
                    idx++;
                }
                count = 1;
            }
        }
        return idx + 1;
    }

    //Best solution.
    // In this code, "j" keeps track of the values such that i, j-1, j-2 is always satisfies the condition.
    // Thus, i th element is compared with (j-2)th element. If same, j remains where it is. If different, j's position
    // is filled with i th value.
    //Quoting the approach
    /*
    The variable j is used to keep track of the current position in the modified array where elements are being stored
    without violating the constraint. The loop iterates through the array, and for each element, it checks whether it is
    the same as the element two positions behind the current j. If it is, it means there are already two occurrences of
    this element in the modified array, and we should skip adding another one to adhere to the constraint. Otherwise,
    the element is added to the modified array at position j, and j is incremented.
     */
    public int removeDuplicates(int[] nums) {
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (j == 1 || nums[i] != nums[j - 2]) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    public static void main(String[] args) {
        RemoveDuplicates2 removeDuplicates = new RemoveDuplicates2();
        System.out.println(removeDuplicates.removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
    }
}
