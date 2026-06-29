class Solution {
    public int largestAltitude(int[] gain) {
        int[] altitudes = new int[gain.length+1];
        altitudes[0] = 0;
        for(int i = 0 ; i < gain.length;i++){
            altitudes[i+1] = altitudes[i] + gain[i];
        }

        return findmax(altitudes);
    }

    public int findmax(int[] arr){
        int max = arr[0];
        for(int  i =0 ; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }
}