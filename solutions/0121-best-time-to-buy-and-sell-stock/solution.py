class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        if(len(prices)==0):
            return 0
        profit=0
        minprice=float('inf')
        for i in range(len(prices)):
            if(prices[i]<minprice) :
                minprice= prices[i]

            
            elif(prices[i]-minprice > profit) :
                profit=prices[i]-minprice 
            

        return profit
