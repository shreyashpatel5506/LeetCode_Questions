var targetIndices = function(nums, target) {
    let index = [];

    // Sort the array (ascending)
    nums.sort((a, b) => a - b);

    // Find indices where the value equals the target
    for (let i = 0; i < nums.length; i++) {
        if (nums[i] === target) {
            index.push(i);
        }
    }

    return index;
};

