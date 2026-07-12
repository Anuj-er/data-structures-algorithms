class Solution {
    public String convert(String s, int numRows) {
        int size = s.length();
        if (numRows == 1 || numRows >= size) {
            return s;
        }
        char[] chars = s.toCharArray();

        Map<Integer, String> result = new HashMap<>();
        int row = 1;
        boolean up = true;
        for (int i = 0; i < s.length(); i++) {
            result.merge(row, String.valueOf(chars[i]), String::concat);
            if (up) {
                row++;
            } else {
                row--;
            }
            if (row == numRows || row == 1) {
                up = !up;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            sb.append(result.get(i + 1));
        }
        return sb.toString();
    }
}
