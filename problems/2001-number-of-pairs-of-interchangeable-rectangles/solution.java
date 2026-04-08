
class Solution {
    public long interchangeableRectangles(int[][] rectangles) {
    
        Map<String, Long> countMap = new HashMap<>();
        long totalPairs = 0;

        for (int[] rect : rectangles) {
            int w = rect[0];
            int h = rect[1];
        
            int common = gcd(w, h);
            String ratio = (w / common) + "/" + (h / common);
          
            countMap.put(ratio, countMap.getOrDefault(ratio, 0L) + 1);
        }

    
        for (long count : countMap.values()) {
            if (count > 1) {
               
                totalPairs += (count * (count - 1)) / 2;
            }
        }

        return totalPairs;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}