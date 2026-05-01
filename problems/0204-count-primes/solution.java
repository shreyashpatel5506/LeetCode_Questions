class Solution {
    public int countPrimes(int n) {
        if (n <= 2)
            return 0;
        int count = 0;
        boolean[] isNotPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!isNotPrime[i]) {
                count++;
                for (long j = (long) i * i; j < n; j += i) {
                    isNotPrime[(int) j] = true;
                }
            }
        }
        return count;
    }
}