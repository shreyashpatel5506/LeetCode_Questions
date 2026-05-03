class Solution {

        public int reversefun(int num){
            int reverse = 0;
            while(num!=0 && num%10==0){
                num = num/10;
            }
            while(num!=0){
                int digit = num%10;
                    reverse = reverse * 10 + digit;
                num = num/10;
            }
            return reverse;
        }

    public boolean isSameAfterReversals(int num) {
        int reversed1 = reversefun(num);
        int reversed2 = reversefun(reversed1);
        return num==reversed2;
        
    }
}
