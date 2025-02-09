/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement = function(nums) {
    let shortedElement=nums.sort();
    return shortedElement[Math.floor(nums.length/2)]
};
