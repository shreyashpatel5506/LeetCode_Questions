class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> resultCount = new HashMap<>();

        for(int n : nums){
            resultCount.put(n, resultCount.getOrDefault(n, 0) + 1);
        }

        List<Integer> numbers = new ArrayList<>(resultCount.keySet());

        Collections.sort(numbers, (n1, n2) -> resultCount.get(n2) - resultCount.get(n1));

        int[] result = new int[k]; 
        for(int i = 0; i < k; i++){
            result[i] = numbers.get(i);
        }
        return result;
    }
}