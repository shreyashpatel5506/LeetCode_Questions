class Solution {
    public int[] decimalRepresentation(int n) {
        List<Integer> result = new ArrayList<>();
        int count = 1;
        if (n == 0) return new int[]{0};
        while(n>0){
            int digit = n % 10;                
            if(digit != 0){
                result.add(digit * count);
            }
            n /= 10; 
            count *= 10; 
        }
         Collections.reverse(result);
        return result.stream().mapToInt(i -> i).toArray();
    }
}