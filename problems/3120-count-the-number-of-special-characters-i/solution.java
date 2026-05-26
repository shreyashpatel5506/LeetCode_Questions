class Solution {
    public int numberOfSpecialChars(String word) {
        int count = 0;
        HashSet<Character> caps = new HashSet<>();
        HashSet<Character> small = new HashSet<>();
        
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(Character.isLowerCase(ch)){
                small.add(ch);
            }else{
                caps.add(ch);
            }
        }

        for(char ch : caps){
            char lower = Character.toLowerCase(ch);
            if(small.contains(lower)){
                count++;
            } 
        }
        return count;
    }
}