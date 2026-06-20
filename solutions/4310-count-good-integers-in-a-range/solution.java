class Solution {
        private Long[][][][] dp;
        private int K;
        
    public long goodIntegers(long l, long r, int k) {
        this.K=k;
        return solve(r) - solve(l-1);
        }

    private long solve(long num){
        if(num<10){
        if(num <0) return 0;
    }
        String s = Long.toString(num);
        int len = s.length();
        dp = new Long[len][10][2][2];

        return dfs(0,0,true,true,s);
    }

    private long dfs(int idx,int prevDigit,boolean isTight, boolean isLeadingZero, String s){
        if(idx == s.length()){
            return isLeadingZero ? 0 : 1;
        }

        int tightIdx = isTight ? 1 : 0;
        int lzIdx = isLeadingZero ? 1 :0;
        if(!isLeadingZero && dp[idx][prevDigit][tightIdx][lzIdx] != null){
            return dp[idx][prevDigit][tightIdx][lzIdx];
        }
        long count = 0;
        int limit = isTight ? (s.charAt(idx) - '0') : 9;

        for(int digit = 0; digit <=limit ;digit++){
            boolean nextTight = isTight && (digit == limit);

            if(isLeadingZero){
                if(digit == 0){
                    count += dfs(idx+1,0,nextTight,true,s);
                }else{
                     count += dfs(idx+1,digit,nextTight,false,s);
                }}else{
                    if(Math.abs(digit - prevDigit)<= K){
                        count += dfs(idx+1,digit,nextTight,false,s);
                    }
                }
            
            if(!isLeadingZero){
                dp[idx][prevDigit][tightIdx][lzIdx] = count;
            }
        }
        return count;
    }
}
