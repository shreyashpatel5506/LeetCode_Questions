import java.util.ArrayList;

class Solution {
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
            || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    public String reverseVowels(String s) {
        ArrayList<Character> vowels = new ArrayList<>();
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isVowel(c)) {
                vowels.add(c);
            }
        }

        int j = vowels.size() - 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isVowel(c)) {
                str.append(vowels.get(j));
                j--;
            } else {
                str.append(c);
            }
        }

        return str.toString();
    }
}