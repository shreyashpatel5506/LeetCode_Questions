import java.util.HashMap;

class Solution {
    public int minimumDistance(int[] nums) {
        HashMap<Integer, int[]> map = new HashMap<>();
        int minTotalDist = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];

            if (!map.containsKey(currentNum)) {
                map.put(currentNum, new int[]{i, -1});
            } else {
                int[] indices = map.get(currentNum);
                if (indices[1] == -1) {
                    indices[1] = i;
                } else {
                    int dist = calculateDist(indices[0], indices[1], i);
                    minTotalDist = Math.min(minTotalDist, dist);
                    indices[0] = indices[1];
                    indices[1] = i;
                }
            }
        }

        return (minTotalDist == Integer.MAX_VALUE) ? -1 : minTotalDist;
    }

    public int calculateDist(int i, int j, int k) {
        return Math.abs(i - j) + Math.abs(j - k) + Math.abs(k - i);
    }
}