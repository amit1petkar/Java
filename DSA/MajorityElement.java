package DSA;

/*
https://leetcode.com/problems/majority-element/?envType=study-plan-v2&envId=top-interview-150

Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ (floor of n/2. For 7, ⌊n / 2⌋ = ⌊3.5⌋ = 3. So, greater than 3 i.e. 4 or more.) times.
You may assume that the majority element always exists in the array.



Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2


Constraints:

n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109


Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class MajorityElement {
    //best solution. Based on Moore Voting Algo.
    /*
        The algorithm starts by assuming the first element as the majority candidate and sets the count to 1.
        As it iterates through the array, it compares each element with the candidate:
        a. If the current element matches the candidate, it suggests that it reinforces the majority element because it appears again. Therefore, the count is incremented by 1.
        b. If the current element is different from the candidate, it suggests that there might be an equal number of occurrences of the majority element and other elements.
        Therefore, the count is decremented by 1.
        Note that decrementing the count doesn't change the fact that the majority element occurs more than n/2 times.
        If the count becomes 0, it means that the current candidate is no longer a potential majority element. In this case, a new candidate is chosen from the remaining elements.
        The algorithm continues this process until it has traversed the entire array.
        The final value of the candidate variable will hold the majority element.
        Explanation of Correctness:
        The algorithm works on the basis of the assumption that the majority element occurs more than n/2 times in the array. This assumption guarantees that even if the count is reset to 0 by other elements, the majority element will eventually regain the lead.

        Let's consider two cases:

        If the majority element has more than n/2 occurrences:

        The algorithm will ensure that the count remains positive for the majority element throughout the traversal, guaranteeing that it will be selected as the final candidate.
        If the majority element has exactly n/2 occurrences:

        In this case, there will be an equal number of occurrences for the majority element and the remaining elements combined.
        However, the majority element will still be selected as the final candidate because it will always have a lead over any other element.
        In both cases, the algorithm will correctly identify the majority element.

        The time complexity of the Moore's Voting Algorithm is O(n) since it traverses the array once.

        This approach is efficient compared to sorting as it requires only a single pass through the array and does not change the original order of the elements.
     */
    public int majorityElement(int[] nums) {
            int count = 0;
            int candidate = 0;

            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }

                if (num == candidate) {
                    count++;
                } else {
                    count--;
                }
            }

            return candidate;
    }

    //Other obvious solution would be using HashMap (number:count as key:value and return key with max value). But that demands additional memory.
}
