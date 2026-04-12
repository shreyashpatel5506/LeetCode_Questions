class Solution {
    Integer[][][] memo;

    public int minimumDistance(String word) {
        memo = new Integer[word.length()][27][27];
        return solve(word, 0, 26, 26);
    }

    private int solve(String word, int idx, int f1, int f2) {
        if (idx == word.length()) return 0;
        if (memo[idx][f1][f2] != null) return memo[idx][f1][f2];
        int target = word.charAt(idx) - 'A';
        int cost1 = getDist(f1, target) + solve(word, idx + 1, target, f2);
        int cost2 = getDist(f2, target) + solve(word, idx + 1, f1, target);
        return memo[idx][f1][f2] = Math.min(cost1, cost2);
    }

    private int getDist(int from, int to) {
        if (from == 26) return 0; 
        int r1 = from / 6, c1 = from % 6;
        int r2 = to / 6, c2 = to % 6;
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}