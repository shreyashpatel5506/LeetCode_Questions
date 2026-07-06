class Solution {
    public int findMaxdiff(int num) {
        int largest = 0;
        int smallest = 9;
        while (num > 0) {
            int digit = num % 10;
            if (digit > largest) {
                largest = digit;
            }
            if (digit < smallest) {
                smallest = digit;
            }
            num /= 10;
        }
        return largest - smallest;

    }

    public int maxDigitRange(int[] nums) {
        int maxRange = -1;
        int sum = 0;

        for (int num : nums) {
            int original = num;
            int range = findMaxdiff(num);

            if (range > maxRange) {
                maxRange = range;
                sum = original;
            } else if (range == maxRange) {
                sum += original;
            }
        }
            return sum;
    }
}
