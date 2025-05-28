class Solution {
    public int[] arrayRankTransform(int[] arr) {
        HashMap<Integer, Integer> numToRank = new HashMap<>();

        TreeSet<Integer> nums = new TreeSet<>();
        for(int i :arr){
            nums.add(i);
        }
        int rank=1;
        for(int i : nums){
            numToRank.put(i,rank);
            rank++;
        }
        for(int j=0;j<arr.length;j++){
            arr[j] = numToRank.get(arr[j]);
        }
        return arr;
            }
}
