// https://www.geeksforgeeks.org/problems/kth-smallest-element5635/

class Solution {
    
    private static void mergeSort(int[] arr){
    int len = arr.length;

    // If the array has less than 2 elements, it's already sorted
    if(len < 2){
        return;
    }

    int middleIndex = len / 2;
    int[] leftHalf = new int[middleIndex];
    int[] rightHalf = new int[len - middleIndex];
    
    // Copy elements into left half
    for(int i = 0; i < middleIndex; i++){
        leftHalf[i] = arr[i];
    }

    // Copy elements into right half
    // Subtract middleIndex to start filling from index 0 of rightHalf
    for(int i = middleIndex; i < len; i++){
        rightHalf[i - middleIndex] = arr[i];
    }
    
    // Recursively sort both halves
    mergeSort(leftHalf);
    mergeSort(rightHalf);
    
    // Merge the sorted halves into the original array
    merge(arr, leftHalf, rightHalf);
}

private static void merge(int[] arr , int[] leftHalf , int[] rightHalf){
    int leftSize = leftHalf.length;
    int rightSize = rightHalf.length;
    int i = 0, j = 0, k = 0;
    // i iterates over leftHalf, j over rightHalf, and k over the main array
    while(i < leftSize && j < rightSize){
        if(leftHalf[i] < rightHalf[j]){
            arr[k] = leftHalf[i];
            i++;
        } else {
            arr[k] = rightHalf[j];
            j++;
        }
        k++;
    }
    // Copy remaining elements from leftHalf
    while(i < leftSize){
        arr[k] = leftHalf[i];
        i++; 
        k++;
    }

    // Copy remaining elements from rightHalf
    while(j < rightSize){
        arr[k] = rightHalf[j];
        j++; 
        k++;
    }
} 
    public static int kthSmallest(int[] arr, int k) {
        mergeSort(arr);
        return arr[k-1];
    }
}

// Time Complexity: O(N log N) for merge sort
// Space Complexity: O(N) for the temporary arrays used in merge sort
