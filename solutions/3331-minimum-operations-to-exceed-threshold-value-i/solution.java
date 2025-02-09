class Solution {
    public int minOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int count=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>=k){
                break;
            }
            count+=1;
        }
        return count;
    }
}
