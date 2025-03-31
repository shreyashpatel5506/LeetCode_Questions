class Solution {
    public int minChanges(int n, int k) {
        int count=0;
        while(n > 0 || k>0){
            int nBit= n%2;
            int kBit=k%2;
            if(nBit == 1 && kBit == 0){
                count += 1;
            }
            if(nBit == 0 && kBit == 1){
                return -1;
            }

            n=  (int)Math.floor(n/2);
            k= (int)Math.floor(k/2);
        }
        return count;
    }
}
