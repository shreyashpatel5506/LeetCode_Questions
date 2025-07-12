class Solution {
    public boolean isBoomerang(int[][] points) {
        // 1/2 * |x1(y2 - y3) + x2(y3 - y1) + x3(y1 - y2)|
        // (y2 - y1) * (x3 - x2) != (y3 - y2) * (x2 - x1)
       return ((points[1][1]-points[0][1])*(points[2][0] - points[1][0]) != ((points[2][1]-points[1][1])*(points[1][0] - points[0][0])));
    }
}
