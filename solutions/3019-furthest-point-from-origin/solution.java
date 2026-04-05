class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int count_L = 0;
        int count_R = 0;
        int blank = 0;
        for (int i = 0; i < moves.length(); i++) {
            char move = moves.charAt(i);
            if (move == 'L') {
                count_L++;
            } else if (move == 'R') {
                count_R++;
            } else {
                blank++;
            }
        }
        int netDisplacement = Math.abs(count_L - count_R);
        return netDisplacement + blank;
    }
}
