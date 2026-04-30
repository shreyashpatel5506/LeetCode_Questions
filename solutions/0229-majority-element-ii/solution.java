class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int require = nums.length/3;
        HashMap<Integer, Integer> resultCount = new HashMap<>();

        for(int n : nums){
            resultCount.put(n, resultCount.getOrDefault(n, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : resultCount.entrySet()){
            int key = entry.getKey();
            int fereq = entry.getValue();

            if(fereq > require){
                result.add(key);
            }
        }
        return result;
    }
}
