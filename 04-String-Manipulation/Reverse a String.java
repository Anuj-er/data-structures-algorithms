// https://www.geeksforgeeks.org/problems/reverse-a-string/

class Solution {
    public static String reverseString(String s) {
        // Convert the input string to a character array so we can modify individual characters
        char[] chars = s.toCharArray();

        int left = 0, right = chars.length - 1;

        while (left < right) {
            // Swap characters at left and right indices
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            // Move both pointers toward the center
            left++;
            right--;
        }

        // Convert the modified character array back to a string and return it
        return new String(chars);
    }
}

