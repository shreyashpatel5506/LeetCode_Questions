class Solution:
    def search(self, nums: List[int], target: int) -> int:
        if len(nums) == 1:
            if nums[0] == target :
                return 0
            else :
                return -1

        start = 0
        end = len(nums)-1
            
        while start <= end :
            mid=(start+end)//2
            if nums[mid]==target :
                
                return mid
            elif nums[mid] > target :
                end =mid -1
            else :
                start = mid+1
        return -1
