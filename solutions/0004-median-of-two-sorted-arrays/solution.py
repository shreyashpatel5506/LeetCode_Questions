
class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        mergerd_array=nums1+nums2
        mergerd_array.sort()

        if len(mergerd_array)%2 != 0 :
            final= mergerd_array[len(mergerd_array)//2]
        elif len(mergerd_array) %2==0 :
            mid=len(mergerd_array)//2
            final=(mergerd_array[mid-1]+mergerd_array[mid])/2
        return final
