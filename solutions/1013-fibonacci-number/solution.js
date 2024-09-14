
const fib = function(N) {
    if (N < 0) return 0; 
    if (N === 0) return 0;
    if (N === 1) return 1;

    let num1 = 0;
    let num2 = 1;
    let sum;

    for (let i = 2; i <= N; i++) {
        sum = num1 + num2;
        num1 = num2;
        num2 = sum;
    }
    return sum;
};


