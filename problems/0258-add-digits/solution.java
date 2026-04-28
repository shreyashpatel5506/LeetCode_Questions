class Solution {
    public int addDigits(int num) {
    int returnDigit = Integer.MAX_VALUE;
        while(returnDigit >= 10){
            returnDigit = countdigitsum(num);
            num = returnDigit;
        }

        return returnDigit;
    }
    public int countdigitsum(int digit){
        int sum = 0;
        while(digit>0){
            sum += digit%10;
            digit /= 10;
        }
        return sum;
    }


}