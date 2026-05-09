class Solution {
    public int[] scoreValidator(String[] events) {
        int[] result = new int[2];
        int totalScore= 0 ;
        int count = 0 ;
        for(int i=0;i<events.length; i++){
            String e = events[i];
            if(e.equals("W")){
               
                count++;
                if(count == 10){break;}
            }
            else if(e.equals("WD")){
                totalScore += 1 ;
            }
            else if(e.equals("NB")){
                totalScore += 1;
            }
            else{
                totalScore += Integer.parseInt(e);
            }
        }
        result[0] = totalScore;
        result[1] = count;
        return result;
    }
}
