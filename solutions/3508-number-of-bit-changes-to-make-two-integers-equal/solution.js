
var minChanges = function(n, k) {
    let count=0;

    while(n>0 || k>0){
        let nBit=n%2;
        let kBit=k%2;
        if(nBit==0 && kBit==1){
            return -1;
        }
        if(nBit==1 && kBit==0){
            count +=1
        }
        n=Math.floor(n / 2);
        k=Math.floor(k / 2);
        
    }
    return count; 
};
