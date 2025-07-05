class Solution {
    public int maximumWealth(int[][] accounts) {
        int total=0;
        for(int i=0;i<accounts.length;i++){
                int totals =0;
            for(int j=0;j<accounts[i].length;j++){
                totals += accounts[i][j];
            }
            if(totals > total){
                total = totals;
            }
        }
        return total;
    }
}
