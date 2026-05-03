class Solution {
    public int sumOfPrimesInRange(int n) {
        int anotherindex = reverseNumber(n);
        int start = Math.min(n, anotherindex);
        int end = Math.max(n, anotherindex);
        
        int sum = 0;
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }
        return sum;
    }

    public boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false; 
            }
        }
        return true; 
    }

    public int reverseNumber(int num) {
        int reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num = num / 10;
        }
        return reversed;
    }
}