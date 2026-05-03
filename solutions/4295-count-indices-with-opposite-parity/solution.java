class Solution {
    public int[] countOppositeParity(int[] nums) {
        int n = nums.length;
       
        int[] result = new int[n]; 

        for (int i = 0; i < n; i++) {
            
            if (nums[i] % 2 == 0) {
                result[i] = numberofodd(nums, i + 1);
            } else {
                result[i] = numberofeven(nums, i + 1);
            }
        }
        // 3. Return must be OUTSIDE the for loop
        return result; 
    }

    public int numberofodd(int[] nums, int start) {
        int count = 0;
        for (int i = start; i < nums.length; i++) {
           
            if (nums[i] % 2 != 0) {
                count++;
            }
        }
        return count;
    }

    public int numberofeven(int[] nums, int start) {
        int count = 0;
        for (int i = start; i < nums.length; i++) {
          
            if (nums[i] % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}
