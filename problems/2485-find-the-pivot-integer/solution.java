class Solution {
     public static int sum(int start, int end){
        int result =0;
        for(int i =start;i<=end;i++){
            result += i ;
        }
        return result;
    }
    public int pivotInteger(int n) {
        int start = 1; 
        int end = n;
        int mid = (start + end )/2;

        for(int i=mid;i<=end;i++){
        int startsum = sum(0,mid);
        int endsum= sum(mid,n);
            if(startsum < endsum){
                if(mid<end){
                    mid +=1;
                }else{
                    return -1;
                }
            }
            else if(startsum == endsum){
                return mid;
            }
            else{
                return -1;
            }
        }
        return -1;
    }
}