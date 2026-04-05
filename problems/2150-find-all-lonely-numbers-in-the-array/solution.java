class Solution {
    public List<Integer> findLonely(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
            if (countMap.get(num) == 1 && 
                !countMap.containsKey(num - 1) && 
                !countMap.containsKey(num + 1)) {
                result.add(num);
            }
        }
        return result;
    }
}