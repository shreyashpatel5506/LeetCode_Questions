
var solution = function(isBadVersion) {
    return function(n) {
       let left = 1, right = n;
       while (left <= right){
        let middle = Math.round((left + right) / 2);
        if(isBadVersion(middle)){
            if(!isBadVersion(middle - 1)){
                return middle;
            }
            else right = middle - 1;
        }
        else left = middle  + 1;
       }
    };
};