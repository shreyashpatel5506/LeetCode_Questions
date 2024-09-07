var isPalindrome = function(x) {
    y=x.toString()
    v=y.split('').reverse().join('');

    result = false;    
    if (v==y){
       result = true;
    }
    else{
        result=false;
    }
    return result;
    
};
