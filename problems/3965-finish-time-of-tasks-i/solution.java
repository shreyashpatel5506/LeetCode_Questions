class Solution {
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < n ;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u= edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            
        }
        long[] finishTimes = new long[n];

        dfs(0,adj,baseTime,finishTimes);

        return finishTimes[0];
    }
    private void dfs(int node , List<List<Integer>> adj,int[] baseTime, long[] finishTimes){
        List<Integer> children = adj.get(node);
        if(children.isEmpty()){
            finishTimes[node] = baseTime[node];
            return;
        }
        long earliest=Long.MAX_VALUE;
        long latest = Long.MIN_VALUE;

        for(int child : children){
            dfs(child,adj,baseTime ,finishTimes);
            long childTime = finishTimes[child];
            earliest = Math.min(earliest,childTime);
            latest = Math.max(latest,childTime);
        }

        long ownDuration = (latest - earliest) + baseTime[node];
        finishTimes[node] = latest + ownDuration;
    }
}