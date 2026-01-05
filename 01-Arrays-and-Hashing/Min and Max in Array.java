// https://www.geeksforgeeks.org/problems/find-minimum-and-maximum-element-in-an-array4428/

class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}


class Solution {
    public Pair<Integer, Integer> getMinMax(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Loop through the array to find min and max
        for (int num : arr) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }
        // Return the result as a Pair
        return new Pair<>(min, max);
    }
}
// Time Complexity: O(N)
// Space Complexity: O(1) since we are using only a constant amount of space