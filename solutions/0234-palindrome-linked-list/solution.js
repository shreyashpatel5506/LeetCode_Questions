var isPalindrome = function(head) {
   const lastVals= [];
   while(head){
    lastVals.push(head.val)
    head=head.next
   }
    let left=0;right=lastVals.length -1;
    while(left < right && lastVals[left] === lastVals[right]){
        left++;
        right--;
    }
    return left >=right
};
