class Solution {
    public int primePalindrome(int n) {
        return findPalindromeprime(n);
    }

    public int findPalindromeprime(int n) {
        int number = n;
         if (number > 10000000 && number < 100000000) {
                number = 100000000; 
            }
        while (true) {
            if (palindrome(number) && isPrime(number)) {
                return number; 
            }
            number++;
        }
    }

    public boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) { 
            if (num % i == 0) {
                return false;
            }
        }
        return true; 
    }
    public boolean palindrome(int n) {
        if (n < 0) return false;
        int original = n;
        int reversed = 0;
        while (n > 0) {
            int lastDigit = n % 10;
            reversed = (reversed * 10) + lastDigit;
            n = n / 10;
        }
        
        return original == reversed;
    }
}
