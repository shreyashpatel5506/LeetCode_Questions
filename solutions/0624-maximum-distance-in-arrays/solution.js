var maxDistance = function(arrays) {
  if (arrays.length === 0) {
    return 0;
  }
  
  let minval = arrays[0][0];
  let maxval = arrays[0][arrays[0].length - 1];
  let distance = 0;

  for (let i = 1; i < arrays.length; i++) {
    let curArray = arrays[i];
    
    // Compute the distance using the current minval and maxval
    distance = Math.max(
      distance,
      Math.max(curArray[curArray.length - 1] - minval, 
               maxval - curArray[0])
    );
    
    // Update minval and maxval
    minval = Math.min(minval, curArray[0]);
    maxval = Math.max(maxval, curArray[curArray.length - 1]);
  }

  return distance;
};

