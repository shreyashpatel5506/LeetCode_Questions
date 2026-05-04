class Solution {
    public boolean isSubstringPresent(String s) {
       String reversed = new StringBuilder(s).reverse().toString();

       for(int i =0; i<s.length()-1; i++){
            String sub = s.substring(i, i + 2);
            if (reversed.contains(sub)) {
                return true;
            }
       }
        return false;
    }
}
