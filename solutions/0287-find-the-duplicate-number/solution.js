/**
 * @param {number[]} nums
 * @return {number}
 */
var findDuplicate = function(nums) {
    let Numsset=new Set();
    let dnum=0;
    for(let i=0;i<nums.length;i++){
        if(Numsset.has(nums[i])){
            dnum=nums[i];
        }
        Numsset.add(nums[i]);
    }
    return dnum;
};
