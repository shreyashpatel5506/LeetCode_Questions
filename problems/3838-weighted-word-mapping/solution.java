class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i<words.length;i++){
            int sum = wordsum(words[i] , weights);
            result.append(outputletter(sum));
        }

        return result.toString();
    }
    public int charvalue(char letter, int[] weights){
        char lowerLetter = Character.toLowerCase(letter);
    if (lowerLetter >= 'a' && lowerLetter <= 'z') {
        int index = lowerLetter - 'a';
        return weights[index];
    }
    return 0;
    }
    public int wordsum(String word , int[] weights){
        char[] letters = word.toCharArray();
         int sum = 0;
        for(int i = 0 ; i < letters.length ; i++){
            sum+= charvalue(letters[i] ,weights);
        }
        return sum;
    }

    public char outputletter(int sum){
        int index = ((sum % 26) + 26) % 26;
        return (char) ('z' - index);
    }
}