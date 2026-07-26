class Solution {
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE,max2 = Integer.MIN_VALUE,max3 = Integer.MIN_VALUE; 
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

          for(int digit : nums){
           
           if(digit >= max1){
                max3 = max2;
                max2 = max1;
                max1= digit;
            }
            else if(max2 <= digit){
                max3 = max2;
                max2 = digit;
            }
            else if(max3<= digit){
                max3= digit;

            }
             // 2. Track the two smallest numbers
            if (digit <= min1) {
                min2 = min1;
                min1 = digit;
            } else if (digit <= min2) {
                min2 = digit;
            }

        }

return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}
