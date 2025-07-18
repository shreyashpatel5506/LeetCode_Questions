class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] sqrArr= new int[nums.length];
        for(int i=0;i<nums.length;i++){
            sqrArr[i] = nums[i]*nums[i];
        }
        Arrays.sort(sqrArr);

        return sqrArr;
    }
}
