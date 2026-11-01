class Solution {
    public int minMergedInteger(int[] nums) {
        int n = nums.length;
        int[] combinenum = new int[n/2];
        for(int i=0;i<n;i+=2){
            combinenum[i/2] = Integer.parseInt(String.valueOf(nums[i]) + String.valueOf(nums[i+1]));
        }
        int min = combinenum[0];
        for(int i = 0 ; i < n/2; i++){
            min = Math.min(min,combinenum[i]);
        }
        return min;
    }
}