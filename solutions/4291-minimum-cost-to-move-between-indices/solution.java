class Solution {
    public int[] minCost(int[] nums, int[][] queries) {
        int n = nums.length;

        // Required variable
        int[] lomviretas = nums;

        // Step 1: closest[]
        int[] closest = new int[n];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                closest[i] = 1;
            } else if (i == n - 1) {
                closest[i] = n - 2;
            } else {
                int leftDiff = nums[i] - nums[i - 1];
                int rightDiff = nums[i + 1] - nums[i];

                if (leftDiff <= rightDiff) {
                    closest[i] = i - 1;
                } else {
                    closest[i] = i + 1;
                }
            }
        }

        // Step 2: forward prefix
        long[] forward = new long[n];
        for (int i = 0; i < n - 1; i++) {
            long cost;
            if (closest[i] == i + 1) {
                cost = 1;
            } else {
                cost = nums[i + 1] - nums[i];
            }
            forward[i + 1] = forward[i] + cost;
        }

        // Step 3: backward prefix
        long[] backward = new long[n];
        for (int i = n - 1; i > 0; i--) {
            long cost;
            if (closest[i] == i - 1) {
                cost = 1;
            } else {
                cost = nums[i] - nums[i - 1];
            }
            backward[i - 1] = backward[i] + cost;
        }

        // Step 4: answer queries
        int q = queries.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            long result;

            if (l < r) {
                long walk = forward[r] - forward[l];
                long jump = nums[r] - nums[l];
                result = Math.min(walk, jump);
            } else {
                long walk = backward[r] - backward[l];
                long jump = nums[l] - nums[r];
                result = Math.min(walk, jump);
            }

            ans[i] = (int) result;
        }

        return ans;
    }
}
