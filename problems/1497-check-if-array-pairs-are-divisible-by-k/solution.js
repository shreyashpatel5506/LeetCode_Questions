/**
 * @param {number[]} arr
 * @param {number} k
 * @return {boolean}
 */
var canArrange = function(arr, k) {
    let arr2=new Array(k).fill(0);
    for(let num of arr){
        let reminder=((num%k)+k)%k;
        arr2[reminder]++;
    }
    if(arr2[0] %2 !==0) return false;

    for(let i=1;i<Math.floor(k/2)+1;i++){
        if(arr2[i] != arr2[k-i]) return false
    }
    return true;
};