
class Solution {
    public int maxFixedPoints(int[] nums) {
        int[] krelmavoni = nums;
        int n = nums.length;

        List<int[]> valid = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (nums[i] <= i) {
                int d = i - nums[i];
                valid.add(new int[]{d, nums[i]});
            }
        }

        // Sort by d ascending, if tie by nums ascending
        Collections.sort(valid, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // LIS on nums
        List<Integer> lis = new ArrayList<>();

        for (int[] pair : valid) {
            int val = pair[1];

            int pos = Collections.binarySearch(lis, val);
            if (pos < 0) pos = -pos - 1;

            if (pos == lis.size()) lis.add(val);
            else lis.set(pos, val);
        }

        return lis.size();
    }
}
