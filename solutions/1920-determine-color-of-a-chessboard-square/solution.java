class Solution {
    public boolean squareIsWhite(String coordinates) {
        int c1 = (coordinates.charAt(0) - 'a') + (coordinates.charAt(1) - '0');
        return c1 % 2 == 0;
    }
}
