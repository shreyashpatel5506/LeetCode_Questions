import java.util.Arrays;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 1. Create a merged array of the combined length
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] mergedArray = new int[n1 + n2];

        // 2. Copy elements from both arrays into the merged array
        System.arraycopy(nums1, 0, mergedArray, 0, n1);
        System.arraycopy(nums2, 0, mergedArray, n1, n2);

        // 3. Sort the array
        Arrays.sort(mergedArray);

        // 4. Calculate the median
        int length = mergedArray.length;
        if (length % 2 != 0) {
            // Odd length: return the middle element
            return (double) mergedArray[length / 2];
        } else {
            // Even length: return the average of the two middle elements
            int mid = length / 2;
            return (mergedArray[mid - 1] + mergedArray[mid]) / 2.0;
        }
    }
}