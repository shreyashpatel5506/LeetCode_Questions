class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int start =0;
        int end=0;
        
        for(int i=0;i<weights.length;i++){
            start = Math.max(start,weights[i]);
            end += weights[i];
        }

        while(start < end){
            int mid = start + ((end- start)/2);
            int sum=0;
            int day= 1;
            for(int num : weights){
                if(sum +num > mid){
                    sum = num;
                    day++;
                }else{
                    sum += num;

                }
            }
            if(day > days){
                start = mid +1;
            }else{
                end = mid ;
            }
        }
        return start;
    }
    }