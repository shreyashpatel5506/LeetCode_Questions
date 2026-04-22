class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] stableNumbers = new int[n];

        for (int i = 0; i < n; i++) {
            int maxLeft = maxNumber(nums, 0, i);
            int minRight = minNumber(nums, i, n - 1);
            stableNumbers[i] = Math.abs(maxLeft - minRight);
        }

        for (int i = 0; i < n; i++) {
            if (stableNumbers[i] <= k) {
                return i;
            }
        }

        return -1; 
    }

    private int maxNumber(int[] nums, int start, int end) {
        int maxVal = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }
        }
        return maxVal;
    }

    private int minNumber(int[] nums, int start, int end) {
        int minVal = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            if (nums[i] < minVal) {
                minVal = nums[i];
            }
        }
        return minVal;
    }
}
