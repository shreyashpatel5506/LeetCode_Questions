import java.util.HashMap;

class Solution {
    public int rearrangeCharacters(String s, String target) {
        HashMap<Character, Integer> sFreq = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            sFreq.put(letter, sFreq.getOrDefault(letter, 0) + 1);
        }
        HashMap<Character, Integer> targetFreq = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            char letter = target.charAt(i);
            targetFreq.put(letter, targetFreq.getOrDefault(letter, 0) + 1);
        }
        int maxCopies = Integer.MAX_VALUE;
        for (char letter : targetFreq.keySet()) {
            int available = sFreq.getOrDefault(letter, 0);
            int needed = targetFreq.get(letter);
            maxCopies = Math.min(maxCopies, available / needed);
        }
        
        return maxCopies;
    }
}
