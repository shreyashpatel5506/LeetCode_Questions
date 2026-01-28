class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
      List<Integer> result = new ArrayList<>();

        for(int i=left;i<=right;i++){
             if(selfdriving(i) == 1){
                result.add(i);
             }
        }

        return result;
    }

    public int selfdriving(int number){
        int temp = number;
    
    while (temp > 0) {
        int digit = temp % 10;
        if (digit == 0 || number % digit != 0) {
            return 0;
        }
        temp /= 10; 
    }
    return 1; 
    }
}
