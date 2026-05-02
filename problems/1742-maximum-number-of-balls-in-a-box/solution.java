class Solution {
    public int countBalls(int lowLimit, int highLimit) {
        HashMap<Integer, Integer> balls = new HashMap<>();


        for(int i = lowLimit ; i <= highLimit ; i++){
             int boxNumber = sumlimit(i);
            balls.put(boxNumber , balls.getOrDefault(boxNumber, 0) + 1);
        }

        Integer maxValue = Collections.max(balls.values());
        return maxValue;
    }
    public int sumlimit(int n){
        int sum = 0;
        while(n>0){
            sum += n%10;
            n /= 10;
        }
        return sum;
    }
}