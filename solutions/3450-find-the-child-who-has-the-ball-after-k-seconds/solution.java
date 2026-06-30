class Solution {
    public int numberOfChild(int n, int k) {
       int ball = 0;
       int direction = 1;
       for(int i = 0 ; i < k ; i++){
        if(direction ==1){
            if(ball == n-1) direction = -1;
            ball += direction;
        }else{
            if(ball ==0) direction = 1;
            ball += direction;
        }


       }
       return ball;
    }
}
