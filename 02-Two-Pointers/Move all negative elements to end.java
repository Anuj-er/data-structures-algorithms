// https://www.geeksforgeeks.org/problems/move-all-negative-elements-to-end1813/1


class Solution {
    public void segregateElements(int[] arr) {
        int len = arr.length;
        int[] posArr = new int[len]; // Array to store positive (and zero) elements
        int[] negArr = new int[len]; // Array to store negative elements
        int pos = 0 , neg = 0;

        // Separate positive and negative elements
        for(int num : arr){
            if(num >= 0){
                posArr[pos++] = num;
            } else {
                negArr[neg++] = num;
            }
        }

        // Copy positive elements back to original array
        for(int i = 0; i < pos; i++){
            arr[i] = posArr[i];
        }

        // Append negative elements after positive elements
        for(int i = 0; i < neg; i++){
            arr[i + pos] = negArr[i];
        }
    }
}

// Time Complexity: O(N)
// Space Complexity: O(N) for storing positive and negative elements separately
