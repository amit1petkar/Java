package EasyLevel;

/*
Write a function that returns the largest element in a given list/array.
 */
public class LargestElement {
    public static void main(String[] args) {
        int []arr = {-5,-2,-3,-4,-6};

        int max = arr[0];

        for(int i=1;i<arr.length;i++){
            if(arr[i]>max){
                max = arr[i];
            }
        }

        System.out.println("MAX VALUE:"+max);
    }
}
