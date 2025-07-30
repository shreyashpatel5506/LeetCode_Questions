class Solution {
    public int triangularSum(int[] nums) {
        while(nums.length > 1){
            int dummy[] = new int[nums.length- 1];
            for(int i = 0;i<dummy.length;i++){
                dummy[i] = (nums[i] +nums[i+1]) % 10;
            }
            nums = dummy;
        }
        return nums[0];
    }
}