class Solution:
    def averageValue(self, nums: List[int]) -> int:
        count=0
        total=0
        for i in range(len(nums)):
            if nums[i]%2==0:
                if nums[i]%3==0:
                    count += 1
                    total += nums[i]
        if count==0:
            return 0
        avg = int(total / count )
        return avg
