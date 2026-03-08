import java.util.*;

class Solution {
    public int splitNum(int num) {
        List<Integer> digits = new ArrayList<>();
        
        while (num > 0) {
            digits.add(num % 10);
            num /= 10;
        }
    
        Collections.sort(digits);
        
        int num1 = 0;
        int num2 = 0;

        for (int i = 0; i < digits.size(); i++) {
            if (i % 2 == 0) {
                num1 = num1 * 10 + digits.get(i);
            } else {
                num2 = num2 * 10 + digits.get(i);
            }
        }
        return num1 + num2;
    }
}