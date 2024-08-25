
var countSeniors = function(details) {
    let count=0;
   for(let i=0;i<details.length;i++){
        if(details[i][11]>=6 && details[i][12]>=0){
            if(details[i][11]==6 && details[i][12]==0){
                continue;
            }
            count++;
        }
   }
   return count;
};
