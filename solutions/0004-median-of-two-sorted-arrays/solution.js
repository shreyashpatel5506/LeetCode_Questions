/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var findMedianSortedArrays = function(nums1, nums2) {
   let mergerd_array = [...nums1, ...nums2];
        mergerd_array.sort((a, b) => a - b);
    let final;
        if ((mergerd_array.length )%2 != 0){
             final= mergerd_array[Math.floor(mergerd_array.length/2)]
        }
        else {
           let  mid=mergerd_array.length / 2
         final=(mergerd_array[mid-1]+mergerd_array[mid])/2
        }
return final;
};
