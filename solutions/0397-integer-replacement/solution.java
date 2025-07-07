class Solution {
    public int integerReplacement(int n) {
        if (n <= 1) {
            return 0;
        }
        long num = n; // use long to avoid overflow
        int count = 0;
        while (num > 1) {
            if (num % 2 != 0) {
                if (num == 3 || num % 4 == 1) {
                    num -= 1;
                } else {
                    num += 1;
                }
            } else {
                num /= 2;
            }
            count++;
        }
        return count;
    }
}

