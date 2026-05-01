class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

       List<String> candidates = new ArrayList<>(counts.keySet());
        
        Collections.sort(candidates, (w1, w2) -> {
            if (counts.get(w1).equals(counts.get(w2))) {
                return w1.compareTo(w2);
            }
            return counts.get(w2) - counts.get(w1);
        });
        return candidates.subList(0, k);
    }
}
