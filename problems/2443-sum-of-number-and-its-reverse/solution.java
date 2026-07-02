class Solution {
    public boolean sumOfNumberAndReverse(int num) {
       for(int i = num/2; i <= num;i++){
        int sum = i + reversedNumber(i);
        if(sum == num){
            return true;
        }
       }
       return false;
    }
    public int reversedNumber(int num){
        
        String reversedStr = new StringBuilder(String.valueOf(num)).reverse().toString();
        int reversednumber = Integer.parseInt(reversedStr);
        return reversednumber;
    }
}