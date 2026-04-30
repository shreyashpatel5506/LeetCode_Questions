class Solution {
    public int balancedStringSplit(String s) {
        int count = 0;
        int L_Count = 0;
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == 'L') {
                L_Count++;
            } else {
                L_Count--;
            }

            if (L_Count == 0) {
                count++;
            }

        }
        return count;
    }
}