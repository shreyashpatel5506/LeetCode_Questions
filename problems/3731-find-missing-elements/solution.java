class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int small = Smallest(nums);
        int large = Largest(nums);
        List<Integer> missing = new ArrayList<>();
        Set<Integer> presentNumbers = new HashSet<>();
        for (int num : nums) {
            presentNumbers.add(num);
        }
        for (int curr = small; curr <= large; curr++) {

            if (!presentNumbers.contains(curr)) {
                missing.add(curr);
            }
        }

        return missing;
    }

    public int Smallest(int[] nums) {
        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        return min;
    }

    public int Largest(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}