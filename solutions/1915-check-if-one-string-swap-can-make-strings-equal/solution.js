/**
 * @param {string} s1
 * @param {string} s2
 * @return {boolean}
 */
var areAlmostEqual = function(s1, s2) {
    let count=0; 
    s1Arrs = s1.split('');
    s2Arrs = s2.split('');
    s1Arr = s1.split('');
    s2Arr = s2.split('');
    s1Arr.sort()
    s2Arr.sort()
    for(let i=0;i<s1Arr.length;i++){
        if(s1Arr[i] != s2Arr[i]){
            return false;
        }
        else{
            if(s1Arrs[i] != s2Arrs[i]){
                count += 1;
            }
        }
    }
    if(count == 2 || count==0){
        return true;
    }
    return false;
};
