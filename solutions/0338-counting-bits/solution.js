/**
 * @param {number} n
 * @return {number[]}
 */

var countBits = function(n) {
function decimalToBinary(num) {
        let binary = "";
        while (num > 0) {
            binary = (num % 2) + binary; // Append remainder (0 or 1)
            num = Math.floor(num / 2); // Divide by 2
        }
        return binary === "" ? "0" : binary; // Handle case when num = 0
    }
    let ans =[];
    for(let i=0;i<=n;i++){
        let count=0;
        let bit=decimalToBinary(i);
        let bitArr= bit.split('')
        for(let j=0;j<bitArr.length;j++){
            if(bitArr[j]==1){
                count += 1;
            }
        }
        ans[i] = count;
    }
    return ans;
};
