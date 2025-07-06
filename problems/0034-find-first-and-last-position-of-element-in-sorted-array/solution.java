class Solution {
    public static int findindex(int[]  nums,int target,boolean startIndex){
        int ans=-1;
        int start=0;
        int end = nums.length-1;
        while ( start <= end){
            int mid = start + ((end - start)/2);
            if(target < nums[mid]){
                end = mid -1;
            }else if(target > nums[mid]){
                start = mid +1;
            }
            else{
                if(startIndex){
                    end = mid -1;
                }else{
                    start = mid+1;
                }
                ans = mid;
               
            }
            }
            return ans;
        }
    
    public int[] searchRange(int[] nums, int target) {
        int[] ans=new int[2];
        int start = findindex(nums,target,true);
        int end = findindex(nums,target,false);
        ans[0] = start;
        ans[1] = end;
        return ans;

    }
}