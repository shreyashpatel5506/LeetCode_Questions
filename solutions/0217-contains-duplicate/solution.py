
class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        
        result = set(nums)
        if(len(nums)!=len(result)) :
            ans =True
        if (len(nums)==len(result)) :
            ans =False
        return ans
        
        
