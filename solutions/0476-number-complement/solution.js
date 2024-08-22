function decimalToBinary(decimalNumber) {
    return decimalNumber.toString(2);
}
function binaryToDecimal(binaryString) {
    return parseInt(binaryString, 2);
}
var findComplement = function(num) {
    let num1=decimalToBinary(num);
            let complement='';
    for(let i=0;i<num1.length;i++){
       complement += num1[i] === '0' ? '1' :'0'
    }
let result= binaryToDecimal(complement);
return result;
};

