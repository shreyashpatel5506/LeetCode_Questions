/**
 * @param {Function} fn
 * @return {Function}
 */
var once = function(fn) {
    let time=true;

    return function(...args){
            if(time){
            let result=fn(...args);
            time=false;
        return result;
    }
    else{
        return undefined;
    }
    }
};

 /*
 * onceFn(1,2,3); // 6
 * onceFn(2,3,6); // returns undefined without calling fn
 */
