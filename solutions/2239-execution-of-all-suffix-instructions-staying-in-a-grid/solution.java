class Solution {
    public int[] executeInstructions(int n, int[] startPos, String s) {
        int m = s.length();
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            int currRow = startPos[0];
            int currCol = startPos[1];
            int count = 0;
            for (int j = i; j < m; j++) {
                char move = s.charAt(j);

                if (move == 'U') currRow--;
                else if (move == 'D') currRow++;
                else if (move == 'L') currCol--;
                else if (move == 'R') currCol++;

                if (currRow >= 0 && currRow < n && currCol >= 0 && currCol < n) {
                    count++;
                } else {
                    break;
                }
            }
            result[i] = count;
        
        }
        return result;
    }
}
