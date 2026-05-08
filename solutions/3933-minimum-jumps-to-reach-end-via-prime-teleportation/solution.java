

class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        
        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);
        
        // 1. Sieve to find Smallest Prime Factor (SPF)
        int[] spf = new int[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) {
            if (spf[i] == 0) {
                for (int j = i; j <= maxVal; j += i) {
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }
        
        // 2. Pre-map primes to indices they divide
        List<Integer>[] primeToIndices = new ArrayList[maxVal + 1];
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            while (val > 1) {
                int p = spf[val];
                if (primeToIndices[p] == null) primeToIndices[p] = new ArrayList<>();
                primeToIndices[p].add(i);
                while (val % p == 0) val /= p;
            }
        }
        
        // 3. BFS
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        boolean[] visitedPrime = new boolean[maxVal + 1];
        
        queue.offer(0);
        dist[0] = 0;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == n - 1) return dist[curr];
            
            // Adjacent moves
            for (int next : new int[]{curr - 1, curr + 1}) {
                if (next >= 0 && next < n && dist[next] == -1) {
                    dist[next] = dist[curr] + 1;
                    queue.offer(next);
                }
            }
            
            // Prime Teleportation (only if nums[curr] is prime)
            if (isPrime(nums[curr], spf) && !visitedPrime[nums[curr]]) {
                int p = nums[curr];
                visitedPrime[p] = true;
                if (primeToIndices[p] != null) {
                    for (int targetIdx : primeToIndices[p]) {
                        if (dist[targetIdx] == -1) {
                            dist[targetIdx] = dist[curr] + 1;
                            queue.offer(targetIdx);
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    private boolean isPrime(int n, int[] spf) {
        return n > 1 && spf[n] == n;
    }
}
