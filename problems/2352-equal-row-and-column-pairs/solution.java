class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringBuilder st = new StringBuilder();
            for (int j = 0; j < n; j++) {
                st.append(grid[i][j]).append(",");
            }
            String s = st.toString();
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            StringBuilder st = new StringBuilder();
            for (int j = 0; j < n; j++) {
                st.append(grid[j][i]).append(",");
            }
            String s = st.toString();
            count += map.getOrDefault(s, 0);
        }

        return count;
    }
}