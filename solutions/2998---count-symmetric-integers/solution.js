var countSymmetricIntegers = function(low, high) {
    let count=0;
  for(let n=low;n<=high;n++){
    let numbers=n.toString().split('').map(Number);
    let sum1=0;
    let sum2=0;
    if(numbers.length%2==0){
    for(let j=0;j<numbers.length/2;j++){
        sum1+=numbers[j];
    }
    for(let j=numbers.length/2;j<numbers.length;j++){
        sum2+=numbers[j];
    }
    if(sum1==sum2){
        count++;
    }
    }
  }
  return count;
};
