class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder str = new StringBuilder();
        int l1=0,l2 = 0 ;
        int e1 = word1.length();
        int e2 = word2.length();
        boolean turn1= true;
        while(l1<e1 && l2<e2){
            if(turn1){
                str.append(word1.charAt(l1));
                l1++;
                turn1=false;
            }else{
                str.append(word2.charAt(l2));
                l2++;
                turn1 = true;
            }
        }

        while(l1<e1){
            str.append(word1.charAt(l1));
                l1++;
        }
        while(l2 < e2){
            str.append(word2.charAt(l2));
                l2++;
        }
        return str.toString();
    }
}