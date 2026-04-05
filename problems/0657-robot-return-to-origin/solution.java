class Solution {
    public boolean judgeCircle(String moves) {
        int[] robot = new int[2];
        robot[0]=0;
        robot[1]=0;

        for(int i=0;i<moves.length();i++){
            char move = moves.charAt(i);
            if(move == 'U'){
                robot[1] += 1;
            }
            else if(move == 'D'){
                robot[1] -= 1;
            }
            else if(move == 'L'){
                robot[0] -= 1;
            }
            else{
                robot[0] += 1;
            }
        }
        if(robot[0]==0 && robot[1] == 0){
            return true;
        }
        return false;
    }
}