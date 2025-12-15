class Solution {
    public int[] findErrorNums(int[] nums) {
      int n = nums.length;
        int[] count = new int[nums.length+1];
        int[] ans = new int[2];

        
        for (int num : nums) {
            count[num]++;
        }

        for (int i = 1; i <= n; i++) {
            if (count[i] == 2) {
                ans[0] = i;   
            }
            if (count[i] == 0) {
                ans[1] = i;  
            }
        }

        return ans;
    }
}
