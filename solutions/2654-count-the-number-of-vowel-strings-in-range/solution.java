import java.util.Set;
import java.util.HashSet;

class Solution {
    public int vowelStrings(String[] words, int left, int right) {
        Set<Character> vowels = new HashSet<>();
        for (char c : new char[]{'a', 'e', 'i', 'o', 'u'}) vowels.add(c);
        int count= 0;
        for(int i=left;i<=right;i++){
            String word= words[i];
            char first=word.charAt(0);
            char last=word.charAt(word.length() - 1);
              if (vowels.contains(first) && vowels.contains(last)) {
                count++;
            }
        }
        return count;
    }
}
