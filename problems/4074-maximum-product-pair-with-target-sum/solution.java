class Solution {
    public int[] maxProductPair(int[] nums, int target) {
        int maxProduct = -1;
        int[] bestPair = new int[]{-1 , -1};
        boolean found = false;
        for(int i =0 ; i<nums.length ; i++){
            for(int j=0 ; j < nums.length ; j++){
                if(i != j && nums[i] + nums[j] == target && nums[i]>nums[j]){
                    int product = nums[i] * nums[j];
                    if(!found || product > maxProduct){
                        maxProduct = product;
                        bestPair = new int[]{i , j};
                        found = true;
                    }
                }
            }
        }
        return bestPair;
    }
}