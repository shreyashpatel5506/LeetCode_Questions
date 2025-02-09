class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        nums.sort()
        count=0
        for i in range(len(nums)) :
            if nums[i]>=k :
                break
            count+=1
        return count