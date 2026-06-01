class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int count = 0;
        int costs =0;
        for(int i = cost.length - 1;i>=0;i--){
            if(count==2){
                count = 0;
            }else{
                costs += cost[i];
                count++;
            }
        }
        return costs;
    }
}
