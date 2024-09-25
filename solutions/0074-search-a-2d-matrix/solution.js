/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function(matrix, target) {
    let result=false;
    for(let i=0;i<matrix.length;i++){
        for(let j=0;j<=matrix[i].length;j++){
            if(matrix[i][j]==target){
                result=true;
            }
        }
    }
    return result;
};
