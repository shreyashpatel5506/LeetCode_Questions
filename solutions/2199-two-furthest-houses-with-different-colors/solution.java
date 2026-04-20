class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int left = 0;
        int right = n - 1;
        while (colors[right] == colors[0]) {
            right--;
        }
        int dist1 = right;
        while (colors[left] == colors[n - 1]) {
            left++;
        }
        int dist2 = (n - 1) - left;
        return Math.max(dist1, dist2);
    }
}
