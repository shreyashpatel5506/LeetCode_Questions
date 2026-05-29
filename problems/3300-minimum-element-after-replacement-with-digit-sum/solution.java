class Solution {
    public int minElement(int[] nums) {
        int[] ans = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            ans[i] = sumofnumber(nums[i]);
        }

        return findmin(ans);
    }
    public int sumofnumber(int nums){
        int sum = 0;
        while(nums > 0){
            int digit = nums%10;
            sum += digit;
            nums /= 10;
        }
        return sum;
    }
    public int findmin(int[] arr){
        int min = arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }
}