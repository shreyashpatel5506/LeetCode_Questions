class Solution {
    public int resilientSubarray(int[] nums, int k) {
        int n = nums.length;
        int maxlen = 1;
        int i = 0;
        while(i < n ){
            int r = (int)(((nums[i] % k) +k ) % k);
            int start = i;
            while(i < n && (int)(((nums[i] % k)+k)%k) == r){
                i++;
            }
            int blocklen = i  - start;
            if(r == 0){
                maxlen = Math.max(maxlen, blocklen);
                
            }else{
                long g = gcd(r,k);
                long step = k / g;
                if(blocklen >= 1){
                    long maxM = (blocklen - 1)/step;
                    long bestL = 1 + maxM * step;
                    maxlen = Math.max(maxlen , (int)bestL);
                }
            }
        }
        return maxlen;
    }
    private long gcd(long a , long b){
        while(b != 0){
            long temp = b;
            b = a %b;
            a =temp;
        }
        return a;
    }
}