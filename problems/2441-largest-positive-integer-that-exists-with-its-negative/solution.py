class Solution:
    def findMaxK(self, nums: list[int]) -> int:
        arr = []
        
        for i in range(len(nums)):
            if -nums[i] in nums and nums[i] > 0: 
                arr.append(nums[i])
        
        return max(arr) if arr else -1