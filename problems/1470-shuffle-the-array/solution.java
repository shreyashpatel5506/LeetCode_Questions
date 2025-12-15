class Solution {
    public int[] shuffle(int[] nums, int n) {
         int[] ans = new int[nums.length];

         for(int i=0;i<ans.length;i+=2){
            ans[i]= nums[i/2];
            ans[i+1]=nums[i/2+n];
         }
    return ans;
    }

}