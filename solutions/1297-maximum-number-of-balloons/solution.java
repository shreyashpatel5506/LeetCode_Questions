class Solution {
    public int maxNumberOfBalloons(String text) {
        HashMap<Character,Integer> freqArr = new HashMap<>();

    for (int i = 0; i < text.length(); i++) {
        char letter = text.charAt(i);
        freqArr.put(letter, freqArr.getOrDefault(letter, 0) + 1);
    }

    int count = 0;
    int b = freqArr.getOrDefault('b', 0);
        int a = freqArr.getOrDefault('a', 0);
        int l = freqArr.getOrDefault('l', 0);
        int o = freqArr.getOrDefault('o', 0);
        int n = freqArr.getOrDefault('n', 0);
           l = l / 2;
        o = o / 2;

    count = Math.min(b, Math.min(a, Math.min(l, Math.min(o, n))));
    return count;


    }
}
