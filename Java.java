// Moving all the zero elements to the end of an array while maintaining the order of non-zero elements

public class Java {
    public static void moveZeroes(int[] nums) {
        int lastNonZeroIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroIndex] = nums[i];
                lastNonZeroIndex++;
            }
        }

        for (int i = lastNonZeroIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 3, 12, 0, 5,8,9};
        moveZeroes(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
