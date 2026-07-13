class Solution {
    public boolean isSubsequence(String s, String t) {
        if(s.length() == 0){
            return true;
        }
        if (t.length() < s.length()){
            return false;
        }
        int i = 0;
        int k=0;
        while(i<t.length()){
            if(t.charAt(i) == s.charAt(k)){
                k++;
                if(k == s.length()){
                    return true;
                }
            }
            i++;
        }
        return false;
    }
}