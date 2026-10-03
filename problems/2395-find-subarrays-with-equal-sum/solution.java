class Solution {
    public boolean findSubarrays(int[] nums) {
        HashSet<Long>set=new HashSet<>();
        for(int i=1;i<nums.length;i++){
            long sum=nums[i]+(long)nums[i-1];
            if(set.contains(sum))return true;
            set.add(sum);
        }
        return false;
    }
}