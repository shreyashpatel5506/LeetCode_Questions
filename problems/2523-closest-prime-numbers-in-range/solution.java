import java.util.*;

class Solution {
    public int[] closestPrimes(int left, int right) {
        int[] primes = findPrimes(left, right);
        if (primes.length < 2) {
            return new int[]{-1, -1};
        }

        int minDiff = Integer.MAX_VALUE;
        int[] ans = new int[]{-1, -1};
        for (int i = 0; i < primes.length - 1; i++) {
            int diff = primes[i + 1] - primes[i];
            
            if (diff < minDiff) {
                minDiff = diff;
                ans[0] = primes[i];
                ans[1] = primes[i + 1];
                if (minDiff <= 2) return ans;
            }
        }

        return ans;
    }

    public int[] findPrimes(int left, int right) {
        boolean[] isPrime = new boolean[right + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        for (int p = 2; p * p <= right; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= right; i += p)
                    isPrime[i] = false;
            }
        }
        
        List<Integer> primeList = new ArrayList<>();
        for (int i = Math.max(2, left); i <= right; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }

        return primeList.stream().mapToInt(Integer::intValue).toArray();
    }
}