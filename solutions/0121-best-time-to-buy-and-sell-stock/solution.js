/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function(prices) {
    if(prices.length==0){
        return 0;
    }
    let n=0;
    let minprice=Infinity;
    for(let i=0;i<prices.length;i++){
        if(prices[i]<minprice){
            minprice=prices[i]
        }
        else if(prices[i]-minprice>n){
            n=prices[i]-minprice;
        }
    }
 
    return n;
};
