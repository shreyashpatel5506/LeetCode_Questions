var findMaxK = function(nums) {
    let arr = [];
    
    for (let i = 0; i < nums.length; i++) {
        if (nums.includes(-nums[i]) && nums[i] > 0) {
            arr.push(nums[i]);
        }
    }
    

    return arr.length > 0 ? Math.max(...arr) : -1;
};

