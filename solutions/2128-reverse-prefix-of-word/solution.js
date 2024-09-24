var reversePrefix = function(word, ch) {
    let index=word.indexOf(ch)
    let reversedPrefix = word.slice(0, index + 1).split('').reverse().join('');
    return reversedPrefix + word.slice(index+1);
};
