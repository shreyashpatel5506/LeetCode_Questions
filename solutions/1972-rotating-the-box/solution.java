class Solution {
    public char[][] rotateTheBox(char[][] boxgrid) {
        int m = boxgrid.length;
        int n = boxgrid[0].length;
        char[][] ans = new char[n][m];

        for (char[] i : boxgrid) {
            int empt = n - 1;
            for (int j = n - 1; j >= 0; j--)
                if (i[j] == '*')
                    empt = j - 1;
                else if (i[j] == '#') {
                    i[j] = '.';
                    i[empt--] = '#';
                }

        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) 
                ans[j][m - i - 1] = boxgrid[i][j];

        return ans;
    }
}
