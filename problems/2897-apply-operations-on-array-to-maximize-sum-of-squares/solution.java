class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long mod = (long) 1e9 + 7;
        int[] bits = new int[32];

        for (int num : nums) {
            for (int b = 0; b < 32; b++) {
                if ((num & (1 << b)) != 0) {
                    bits[b]++;
                }
            }
        }

        long res = 0;

        for (int i = 0; i < k; i++) {
            long num = 0;

            for (int b = 0; b < 32; b++) {
                if (bits[b] > 0) {
                    num |= (1L << b);
                    bits[b]--;
                }
            }

            res = (res + num * num % mod) % mod;
        }

        return (int) res;
    }
}