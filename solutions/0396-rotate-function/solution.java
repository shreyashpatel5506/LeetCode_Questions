class Solution {
    public int maxRotateFunction(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        long sum = 0;      
        long currentF = 0; 
        
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            currentF += (long) i * nums[i];
        }
        long maxVal = currentF;
        for (int i = n - 1; i > 0; i--) {
            currentF = currentF + sum - (long) n * nums[i];
            if (currentF > maxVal) {
                maxVal = currentF;
            }
        }
        
        return (int) maxVal;
    }
}
