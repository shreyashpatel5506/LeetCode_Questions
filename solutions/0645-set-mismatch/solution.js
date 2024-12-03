/**
 * @param {number[]} nums
 * @return {number[]}
 */
var findErrorNums = function(nums) {
    let sum = 0;
    let dnum = 0;
    let seen = new Set();

    for (let i = 0; i < nums.length; i++) {
        sum += nums[i];
        if (seen.has(nums[i])) {
            dnum = nums[i];
        }
        seen.add(nums[i]);
    }

    let n = nums.length;
    let actualSum = (n * (n + 1)) / 2;
    let misnum = actualSum - sum + dnum;

    return [dnum, misnum];
};

