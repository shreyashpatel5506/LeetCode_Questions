class Solution {
    public boolean allCaps(String word, int n){
        for(int i=n-2;i>=0;i--){
            if(word.charAt(i)>='A' && word.charAt(i)<='Z'){
                continue;
            }
            return false;
        }
        return true;

    }

    public boolean allSmall(String word, int n){
        for(int i=n-2;i>0;i--){
            if(word.charAt(i)>='A' && word.charAt(i)<='Z'){
                return false;
            }
        }
        return true;
    }

    public boolean detectCapitalUse(String word) {
        if(word.length()==1){
            return true;
        }
        int n = word.length();

        if(word.charAt(n-1)>='A' && word.charAt(n-1)<='Z'){
            return allCaps(word,n);
        }
        return allSmall(word,n);

    }
}