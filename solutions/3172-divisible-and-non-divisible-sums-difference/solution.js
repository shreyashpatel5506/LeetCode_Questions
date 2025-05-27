
var differenceOfSums = function(n, m) {
    let num2=0;
    let num1=0;
    for(let i=1;i<=n;i++){
        if(i%m == 0){
            num2 = num2 + i;
        }
        else{
            num1 =  num1 +i;
        }
    }
    return num1-num2
};
