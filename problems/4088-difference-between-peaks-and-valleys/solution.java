class Solution {
    public int peakValleyDifference(int[] nums) {
        int n = nums.length;
        int peaksum = 0;
        int valleysum = 0;
        for(int i = 1; i < n- 1 ; i++){
            if(nums[i]> nums[i-1] && nums[i]>nums[i+1]){
                peaksum += nums[i];
            }
            else if(nums[i]<nums[i-1] && nums[i]<nums[i+1]){
                valleysum += nums[i];
            }
        }
        return peaksum - valleysum;
    }
}