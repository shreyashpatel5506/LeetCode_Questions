class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(c);
            }
        }

        // Get the cleaned string and reverse it
        String filtered = cleaned.toString();
        String reversed = cleaned.reverse().toString();

        // Compare filtered and reversed
        return filtered.equals(reversed);
    }
}
