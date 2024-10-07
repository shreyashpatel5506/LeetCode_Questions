/**
 * @param {number[][]} transactions
 * @return {number}
 */
var minimumMoney = function(transactions) {
    let maxCost = 0;
    let maxCash = 0;
    let money = 0;
    
    for(let i = 0; i < transactions.length; i++){
        if(transactions[i][0] > transactions[i][1]){
            money += transactions[i][0] - transactions[i][1];
            maxCash = Math.max(maxCash, transactions[i][1]);
        } else{
            maxCost =  Math.max(maxCost, transactions[i][0]);
        }
    }
    return money + Math.max(maxCash, maxCost) ;
};