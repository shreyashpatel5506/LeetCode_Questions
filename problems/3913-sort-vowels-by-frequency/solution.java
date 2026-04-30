
class Solution {
    public String sortVowels(String s) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        HashMap<Character, Integer> firstOccurrence = new HashMap<>();
        List<Character> allVowelsInString = new ArrayList<>();
        
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (isVowel(c)) {
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
                if (!firstOccurrence.containsKey(c)) {
                    firstOccurrence.put(c, i);
                }
                allVowelsInString.add(c);
            }
        }
        Collections.sort(allVowelsInString, (c1, c2) -> {
            int f1 = freqMap.get(c1);
            int f2 = freqMap.get(c2);
            
            if (f1 != f2) {
                return f2 - f1; 
            }
            return firstOccurrence.get(c1) - firstOccurrence.get(c2);
        });

        StringBuilder result = new StringBuilder();
        int j = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (isVowel(charArray[i])) {
                result.append(allVowelsInString.get(j));
                j++;
            } else {
                result.append(charArray[i]);
            }
        }
        return result.toString();
    }

   
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}