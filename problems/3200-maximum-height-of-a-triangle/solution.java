class Solution {
    public int maxHeightOfTriangle(int red, int blue) {
        return Math.max(getHeight(red, blue, true), getHeight(red, blue, false));
    }

    private int getHeight(int red, int blue, boolean isRedTurn) {
        int layer = 1;
        
        while (true) {
            if (isRedTurn) {
                if (red < layer) break;
                red -= layer;
            } else {
                if (blue < layer) break; 
                blue -= layer;
            }
            
            layer++;
            isRedTurn = !isRedTurn; 
        }
        return layer - 1;
    }
}
