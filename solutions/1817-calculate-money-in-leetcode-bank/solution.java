class Solution {
    public int totalMoney(int n) {
        int total = 0;
        int mondayMoney = 1;
        int currentDeposit = 1;

        for (int day = 0; day < n; day++) {
            total += currentDeposit;
            currentDeposit++;
            if ((day + 1) % 7 == 0) {
                mondayMoney++;
                currentDeposit = mondayMoney;
            }
        }
        return total;
    }
}

