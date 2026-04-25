class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(c);
            }
        }
        String filtered = cleaned.toString();
        String reversed = cleaned.reverse().toString();

        return filtered.equals(reversed);
    }
}