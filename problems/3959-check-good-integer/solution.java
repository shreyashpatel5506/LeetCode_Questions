class Solution {
    public boolean checkGoodInteger(int n) {
        int result = sqauresum(n) - digitsum(n);
        if(result >= 50){
            return true;
        }
        return false;
    }
    public int digitsum(int n){
        int sum = 0;
        while(n>0){
            int digit = n %10;
            sum += digit;
            n /= 10;
        }
        return sum;
    }
     public int sqauresum(int n){
         int sum =0;
         while(n>0){
             int digit = n%10;
             int sqauredigit = digit * digit;
             sum+= sqauredigit;
             n /= 10;
         }
         return sum;
     }
}