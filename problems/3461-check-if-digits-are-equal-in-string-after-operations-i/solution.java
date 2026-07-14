class Solution {
    public boolean hasSameDigits(String s) {
        while(s.length() > 2){
            s=afteroperation(s);
        }

        return s.charAt(0) == s.charAt(1);
    }
    public String afteroperation(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i++) {
            int digit1 = s.charAt(i) - '0';
            int digit2 = s.charAt(i + 1) - '0';
            int sum = digit1 + digit2;
            
            result.append(sum % 10);
        }
        return result.toString();
    }
}