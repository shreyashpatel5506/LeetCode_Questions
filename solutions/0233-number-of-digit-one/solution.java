class Solution {
    public int countDigitOne(int n) {
        if (n <= 0) return 0;
        
        int count = 0;
        for (long i = 1; i <= n; i *= 10) {
            long divider = i * 10;
            
            long high = n / divider;
            long curr = (n % divider) / i;
            long low = n % i;
            
            if (curr == 0) {
                count += high * i;
            } else if (curr == 1) {
                count += high * i + low + 1;
            } else {
                count += (high + 1) * i;
            }
        }
        
        return count;
    }
}
