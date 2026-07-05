class Solution {
    public int maxValidPairSum(int[] nums, int k) {
        if (nums == null || nums.length <= k) {
            return -1; 
        }
        int maxSum = -1;
        int maxLeftElement = nums[0]; 
        for (int j = k; j < nums.length; j++) {
            maxLeftElement = Math.max(maxLeftElement, nums[j - k]);
            int currentSum = maxLeftElement + nums[j];
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
