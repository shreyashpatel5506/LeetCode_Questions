
class Solution {
    public int[] frequencySort(int[] nums) {
      
        Map<Integer, Integer> counts = new HashMap<>();
        for (int n : nums) {
            counts.put(n, counts.getOrDefault(n, 0) + 1);
        }

        List<Integer> list = new ArrayList<>();
        for (int n : nums) {
            list.add(n);
        }

        Collections.sort(list, (a, b) -> {
            int freqA = counts.get(a);
            int freqB = counts.get(b);
            if (freqA != freqB) {
                return freqA - freqB;
            } else {
                return b - a; 
            }
        });
        for (int i = 0; i < nums.length; i++) {
            nums[i] = list.get(i);
        }
        
        return nums;
    }
}