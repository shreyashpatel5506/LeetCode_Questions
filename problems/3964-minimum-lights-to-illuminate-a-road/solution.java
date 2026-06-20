class Solution {
    public int minLights(int[] lights) {
        int n = lights.length;
        int[] diff = new int[n+1];
        for(int i = 0 ;i < n ;i++){
           if(lights[i]>0){ int v = lights[i];
            int start = Math.max(0,i-v);
            int end = Math.min(n-1,i+v);
           diff[start] += 1;
            diff[end +1] -=1;               
            }
        }
    boolean[] illuminated = new boolean[n];
       int currentLights=0;
        for(int i=0;i<n;i++){
            currentLights += diff[i];
            if(currentLights > 0){
                illuminated[i] = true;
            }
        }

    int additionalBulbs= 0;
    int i=0;
    while(i< n){
        if(!illuminated[i]){
        additionalBulbs++;
        i = i+3;
    }else{
            i++;
    }
    }
    
    return additionalBulbs;
    }
}