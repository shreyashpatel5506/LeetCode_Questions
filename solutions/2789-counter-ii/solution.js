
var createCounter = function(init) {
    let init1=init;
    function increment(){return  ++init1; }
    function decrement(){return --init1; }
    function reset(){ return (init1=init)}
    return {increment,reset,decrement};
}

