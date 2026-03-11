class Solution {
    public int evenNumberBitwiseORs(int[] nums) {
        List<Integer> even = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                even.add(nums[i]);
            }
        }
        if (even.isEmpty()) return 0;
        int result = even.get(0);
        for (int i = 0; i < even.size(); i++) {
            result |= even.get(i);
        }

        return result;
    }
}
