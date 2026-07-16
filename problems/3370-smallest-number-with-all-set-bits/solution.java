class Solution {
    public int smallestNumber(int n) {
      int result = 0;
        int i = 1;
        while(result < n) {
            result = (int) Math.pow(2, i) - 1;
            i++;
        }
        return result;
    }
}