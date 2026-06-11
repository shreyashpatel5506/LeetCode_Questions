class Solution {
    public int distMoney(int money, int children) {
        int moneyLeft = money - children; 
        if(moneyLeft < 0) 
            return -1; 

        if(moneyLeft / 7 == children && moneyLeft % 7 == 0) 
            return children;
        if(moneyLeft / 7 == children - 1 && moneyLeft % 7 == 3)
            return children - 2;
        int count = (children - 1) < (moneyLeft / 7) ? (children - 1) : (moneyLeft / 7);
        return count;
    }
}
