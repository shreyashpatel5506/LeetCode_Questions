import java.util.HashMap;
import java.util.Map;

class Solution {
    public int mostFrequentEven(int[] nums) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int n : nums) {
            if (n % 2 == 0) {
                counts.put(n, counts.getOrDefault(n, 0) + 1);
            }
        }

        int result = -1;
        int maxFreq = 0;

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();

            if (freq > maxFreq) {
                maxFreq = freq;
                result = num;
            } else if (freq == maxFreq) {
                result = Math.min(result, num);
            }
        }

        return result;
    }
}
