class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int n = costs.length;
        if (n == 0) return 0;

        int maxVal = costs[0];
        for (int i = 1; i < n; i++) {
            if (costs[i] > maxVal) {
                maxVal = costs[i];
            }
        }

        int[] counterArray = new int[maxVal + 1];
        for (int i = 0; i < n; i++) {
            counterArray[costs[i]]++;
        }

        int result = 0;
        for (int price = 1; price <= maxVal; price++) {
            if (counterArray[price] > 0) {
                int quantityToBuy = Math.min(counterArray[price], coins / price);
                
                result += quantityToBuy;
                coins -= quantityToBuy * price; 
                
                if (coins < price) {
                    break;
                }
            }
        }

        return result;
    }
}
