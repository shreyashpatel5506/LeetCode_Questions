class Solution:
    def findClosestNumber(self, nums: List[int]) -> int:
        ans = nums[0]

        for i in range(len(nums)):
            if abs(ans) > abs(nums[i]):
                ans=nums[i]
            elif abs(ans)==abs(nums[i]):
                if nums[i]>ans:
                    ans=nums[i]
        return ans
