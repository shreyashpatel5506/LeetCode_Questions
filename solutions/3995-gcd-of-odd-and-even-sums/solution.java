class Solution {
    public int gcdOfOddEvenSums(int n) {
        int oddSum = n*n;
        int evenSum = n * (n+1);
        return gcd(oddSum , evenSum);
        
    }
    public int gcd(int a , int b){
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
