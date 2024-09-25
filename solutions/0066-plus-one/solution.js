/**
 * @param {number[]} digits
 * @return {number[]}
 */
var plusOne = function(digits) {
    let number = BigInt(digits.join(''));
    number += 1n;
    let result=Array.from(String(number),Number);
    return result;
};
