class Solution {
    public boolean digitCount(String num) {
        int[] counts = new int[10];

        for (int i = 0; i < num.length(); i++) {
            int digit = num.charAt(i) - '0';
            counts[digit]++;
        }

        for (int i = 0; i < num.length(); i++) {
            int expectedCount = num.charAt(i) - '0';
            int actualCount = counts[i];  
            
            if (actualCount != expectedCount) {
                return false;
            }
        }

        return true;
    }
}