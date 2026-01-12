class Solution {
    public int maximum69Number(int num) {
  char[] digits = String.valueOf(num).toCharArray();  
  int max= num;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                if(Integer.parseInt(new String(digits)) > max){
                    max=Integer.parseInt(new String(digits));
                }
                break; 
            }
        }
        return max;
    }
}