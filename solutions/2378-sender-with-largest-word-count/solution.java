import java.util.*;

class Solution {
    public String largestWordCount(String[] messages, String[] senders) {
        HashMap<String, Integer> wordCount = new HashMap<>();
        
        for (int i = 0; i < messages.length; i++) {
            String user = senders[i];
            int currentMessageWords = words(messages[i]);
            wordCount.put(user, wordCount.getOrDefault(user, 0) + currentMessageWords);
        }

        String topSender = "";
        int maxWords = 0;

        for (String user : wordCount.keySet()) {
            int count = wordCount.get(user);
            if (count > maxWords || (count == maxWords && user.compareTo(topSender) > 0)) {
                maxWords = count;
                topSender = user;
            }
        }

        return topSender;
    }

    public int words(String s) {
        if (s == null || s.isEmpty()) return 0;
        
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                count++;
            }
        }
        return count + 1;
    }
}
