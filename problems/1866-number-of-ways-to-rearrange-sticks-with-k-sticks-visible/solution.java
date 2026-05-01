class Solution {
    public int rearrangeSticks(int n, int k) {
        final int MOD = 1_000_000_007;
        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                long waysIfSmallestVisible = dp[i - 1][j - 1];
                
                long waysIfSmallestHidden = (i - 1) * dp[i - 1][j];
                
                dp[i][j] = (waysIfSmallestVisible + waysIfSmallestHidden) % MOD;
            }
        }
        
        return (int) dp[n][k];
    }
}