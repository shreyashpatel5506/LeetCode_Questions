class Solution {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int maxTime = releaseTimes[0];
        char result = keysPressed.charAt(0);
        for (int i = 1; i < releaseTimes.length; i++) {
            int time = releaseTimes[i] - releaseTimes[i - 1];
            if (time > maxTime) {
                maxTime = time;
                result= keysPressed.charAt(i);
            }
            else if(time == maxTime){
                if(keysPressed.charAt(i) > result){
                    result = keysPressed.charAt(i);
                }
            }
        }

        return result;
    }

}

