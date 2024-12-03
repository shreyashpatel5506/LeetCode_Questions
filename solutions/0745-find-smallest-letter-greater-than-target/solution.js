var nextGreatestLetter = function(letters, target) {
    let targets=target.charCodeAt(0);
    let list=[];
    for(i=0;i<letters.length;i++){
        let value=letters[i].charCodeAt(0);
        if(value>targets){
            list.push(letters[i])
        }
    }
  if(list.length == 0){
    return letters[0];
  }
  else{
    return list.sort()[0];
  }

 }

