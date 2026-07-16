class Solution {
    public long gcdSum(int[] nums) {
        int[] prefixGCD = new int[nums.length];
        int max = -1;
        for(int i = 0; i < nums.length ; i++){
            max = Math.max(max , nums[i]);
            prefixGCD[i] = gcd(max,nums[i]);
        }
        Arrays.sort(prefixGCD);
       long sum = 0;
        int i = 0, j = nums.length - 1;

        while (i < j) {
            sum += gcd(prefixGCD[i], prefixGCD[j]);
            i++;
            j--;
        }

        return sum;

    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
