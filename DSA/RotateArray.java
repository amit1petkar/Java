package DSA;

import java.util.ArrayList;
import java.util.Arrays;

public class RotateArray {
    public void rotate(int[] nums, int k) {
        int temp = 0;
        int size = nums.length;
        while(k>0) {
            temp = nums[size-1];
            for(int i=size-2; i>=0; i--) {
                nums[i+1] = nums[i];
            }
            nums[0] = temp;
            k--;
        }
    }

    public static void main(String[] args) {
        RotateArray rotateArray = new RotateArray();
        int[] nums = {1,2,3,4,5,6,7};
        rotateArray.rotate(nums, 3);

    }
}
