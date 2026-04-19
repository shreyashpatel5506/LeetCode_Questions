class Solution {
    public int[] replaceElements(int[] arr) {
        int[] result = new int[arr.length];

        for(int i = 0 ; i < arr.length ; i++){
            result[i] = max(arr , i);

        }
        return result;
    }
    public int max(int[] arr,int start){
        int maxNumber = -1 ;
        for(int i = start+1 ; i < arr.length ; i++){
            if(arr[i] > maxNumber){
                maxNumber = arr[i];
            }
        }
        return maxNumber;
    }
}