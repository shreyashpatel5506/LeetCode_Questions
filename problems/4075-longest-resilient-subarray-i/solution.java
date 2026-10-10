class Solution {
    public int resilientSubarray(int[] nums, int k) {
      if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int maxlen = 1;

        int i = 0;
        while( i < n){
            int r = (nums[i] % k +k) % k;
            int start = i;
            while(i < n && (nums[i] % k + k)%k == r){
                i++;
            }
            int end = i -1;
            int blocklen = end -start +1;
            if(r == 0){
                maxlen = Math.max(maxlen, blocklen);
            }else{
                for(int len = 1; len<=blocklen ; len++){
                    if(((long) len *r)%k == r){
                        maxlen = Math.max(maxlen, len);
                    }
                }
            }
        }
        return maxlen;
    }
}