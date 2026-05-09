class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int numLayers = Math.min(m, n) / 2;

        for (int layer = 0; layer < numLayers; layer++) {
            // 1. Identify the boundaries of the current layer
            int top = layer;
            int left = layer;
            int bottom = m - 1 - layer;
            int right = n - 1 - layer;

            // 2. Extract elements of the layer in counter-clockwise order
            List<Integer> elements = new ArrayList<>();
            
            // Top side
            for (int j = left; j < right; j++) elements.add(grid[top][j]);
            // Right side
            for (int i = top; i < bottom; i++) elements.add(grid[i][right]);
            // Bottom side
            for (int j = right; j > left; j--) elements.add(grid[bottom][j]);
            // Left side
            for (int i = bottom; i > top; i--) elements.add(grid[i][left]);

            // 3. Calculate effective rotation
            int size = elements.size();
            int rotation = k % size;
            
            // 4. Place elements back into the grid starting from the rotated offset
            int index = rotation;
            
            for (int j = left; j < right; j++) grid[top][j] = elements.get(index++ % size);
            for (int i = top; i < bottom; i++) grid[i][right] = elements.get(index++ % size);
            for (int j = right; j > left; j--) grid[bottom][j] = elements.get(index++ % size);
            for (int i = bottom; i > top; i--) grid[i][left] = elements.get(index++ % size);
        }

        return grid;
    }
}