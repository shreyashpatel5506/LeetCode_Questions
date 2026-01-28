class Solution {
    public int countDigits(int num) {
        int count =0;
        int temp =num;
        while(temp >0){
            int n = temp%10;
            if(num%n == 0){
                count++;
            }
            temp /= 10;
        }
        return count;
    }
}