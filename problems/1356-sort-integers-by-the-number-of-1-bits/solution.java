class Solution {
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n; i++) {
            // Transform the number: (Bit count * Offset) + Value
            // Using 10001 because max value in many problems is 10^4
            arr[i] += countBits(arr[i]) * 10001;
        }
        
        // Use standard primitive sort
        java.util.Arrays.sort(arr);
        
        for (int i = 0; i < n; i++) {
            // Restore the original value using modulo
            arr[i] %= 10001;
        }
        
        return arr;
    }

    // Efficient bit counting without Strings
    private int countBits(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1); // Clears the least significant bit
            count++;
        }
        return count;
    }
}