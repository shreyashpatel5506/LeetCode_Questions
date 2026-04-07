class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int[] smallRowwithmax1 = new int[2];
        int maxcount = 0;
        for(int i =0 ;i < mat.length; i++){
            int count = 0;
            for(int j= 0 ;j<mat[i].length;j++){
                if(mat[i][j] ==1 ){
                    count += 1;
                }
            }
            if(count > maxcount){
                maxcount = count;
                smallRowwithmax1[0] = i;
                smallRowwithmax1[1] = maxcount;
            }
        }
        return smallRowwithmax1;
    }
}