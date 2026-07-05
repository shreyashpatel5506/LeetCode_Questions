class Solution {
    public boolean isMiddleElementUnique(int[] nums) {
        HashMap<Integer , Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1); 
        } 

        int middleIndex = nums.length / 2;
        int middleElement = nums[middleIndex];
        return freq.get(middleElement) == 1;
    }
}
