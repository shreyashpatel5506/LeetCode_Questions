class Solution {
    public int mirrorDistance(int n) {
        return Math.abs(n - reverse(n));
    }

    public int reverse(int n){
        int reversed = 0;
        while(n>0){
            int digit = n%10;
            reversed = reversed*10 + digit;
            n /= 10;
        }
        return reversed;
    }
}