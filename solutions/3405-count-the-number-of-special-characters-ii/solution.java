class Solution {
    public int numberOfSpecialChars(String word) {
        int count = 0;
         HashMap<Character, Integer> caps = new HashMap<>();
        HashMap<Character, Integer> small = new HashMap<>();
        
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (Character.isLowerCase(ch)) {
                small.put(ch, i); 
            } else {
                if (!caps.containsKey(ch)) {
                    caps.put(ch, i);
                }
            }
        }

       for (char upperCh : caps.keySet()) {
            char lowerCh = Character.toLowerCase(upperCh);
            
            if (small.containsKey(lowerCh)) {
                int lowerIdx = small.get(lowerCh);
                int upperIdx = caps.get(upperCh);
               
                if (lowerIdx < upperIdx) {
                    count++;
                }
            }
        }
        return count;
    }
}
