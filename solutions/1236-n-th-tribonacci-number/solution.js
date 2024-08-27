
var tribonacci = function(n) {
let tribonakki=[38]
tribonakki[0]=0;
tribonakki[1]=1;
tribonakki[2]=1;
for(let i=3;i<38;i++){
        tribonakki[i]=tribonakki[i-1]+tribonakki[i-2]+tribonakki[i-3];
    }
    return tribonakki[n];   
};
