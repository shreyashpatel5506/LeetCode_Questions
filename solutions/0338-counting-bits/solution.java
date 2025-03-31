class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            String bit = decimalToBinary(i);
            int count = 0;
            
            for (int j = 0; j < bit.length(); j++) {
                if (bit.charAt(j) == '1') {
                    count++;
                }
            }
            ans[i] = count;
        }
        return ans;
    }

    private String decimalToBinary(int num) {
        if (num == 0) return "0"; // Handle edge case explicitly

        StringBuilder binary = new StringBuilder();
        while (num > 0) {
            binary.insert(0, num % 2); // Prepend binary digits
            num /= 2;
        }
        return binary.toString();
    }
}

