var minOperations = function(nums, k) {
  nums.sort((a, b) => a - b);
  let count = 0;
  for (let i = 0; i < nums.length; i++) {
    if (nums[i] >= k) {
      break;
    }
    count++;
  }
  return count;
};

