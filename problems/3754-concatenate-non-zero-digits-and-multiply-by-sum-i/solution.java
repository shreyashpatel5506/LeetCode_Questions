class Solution {
    public long sumAndMultiply(int n) {
        if (n == 0) {
            return 0;
        }

        long sum = 0 ;
        String str = Long.toString(n);
         StringBuilder xStr = new StringBuilder();
          for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch != '0') {
                xStr.append(ch);            
                sum += Character.getNumericValue(ch);
            }
        }

        long x = Integer.parseInt(xStr.toString());
        long result = x * sum;
        return result;
        }
}