var maximumDifference = function(nums) {
   let maxDiff=0
      for(let i=0;i<nums.length;i++){
            for(let j=i;j<nums.length;j++){
                if(nums[j]-nums[i]>maxDiff){
                    maxDiff=nums[j]-nums[i];
                }
            }
      }
      if(maxDiff==0){
        return -1
      }
      return maxDiff;
};
