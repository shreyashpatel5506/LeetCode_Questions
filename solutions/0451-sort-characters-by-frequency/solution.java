
class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        List<Character> characters = new ArrayList<>(freqMap.keySet());
        Collections.sort(characters, (c1, c2) -> freqMap.get(c2) - freqMap.get(c1));
        StringBuilder result = new StringBuilder();
        for (char c : characters) {
            int count = freqMap.get(c);
            for (int i = 0; i < count; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }
}
