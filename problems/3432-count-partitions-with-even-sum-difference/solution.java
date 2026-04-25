class Solution {
    public int countPartitions(int[] nums) {
        int n = nums.length - 1;
        int sumLeft = 0;
        int sumRight = 0;
        int difference = 0;
        int result = 0;

        for(int num : nums) {
            sumRight += num;
        }

        for(int i = 0; i < n; i++) {
            sumLeft += nums[i];
            sumRight -= nums[i];
            difference = sumLeft - sumRight;

            if(difference % 2 == 0) {
                result++;
            }
        }
        return result;
    }
}