class Solution {
    int count=1;
    void dfs(int node,int parent, List<List<Integer>> adj,List<List<Integer>> ans,int [] tin, int [] low, int vis[]){
        vis[node]=1;
    tin[node]= low[node]=count++;
    for(int it: adj.get(node)){
        if(it==parent) continue;
        if(vis[it]==0){
            dfs(it,node,adj,ans,tin,low,vis);
            if(low[it]>tin[node]){
                ans.add(new ArrayList<>(List.of(it,node)));
            }
            else{
                low[node]= Math.min(low[it],low[node]);
            }

        }
        else{
             low[node]= Math.min(low[it],low[node]);
        }
    }
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> adj= new ArrayList<>();
         //making of adj
        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        for(List<Integer> i: connections ){
            adj.get(i.get(0)).add(i.get(1));
            adj.get(i.get(1)).add(i.get(0));
        }
        int [] vis= new int[n];
        int [] low= new int[n];
        int [] tin= new int[n];

        //storing ans 
        List<List<Integer>> ans= new ArrayList<>();

        dfs(1,1,adj,ans,tin,low,vis);

        return ans;
    }
}