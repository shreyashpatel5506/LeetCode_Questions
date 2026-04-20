class Solution {
    public String generateTag(String caption) {
        String cleaned = caption.replaceAll("[^a-zA-Z ]", "");
        String[] words = cleaned.trim().split("\\s+");

        StringBuilder result = new StringBuilder();
                result.append('#');
        if (words.length == 0 || words[0].isEmpty())
            return result.toString();

        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                result.append(words[i].toLowerCase());
            } else {
                result.append(capitalize(words[i]));
            }
        }
        String tag = result.toString();
        return tag.length() > 100 ? tag.substring(0, 100) : tag;
    }

    public String capitalize(String word) {
        if (word == null || word.isEmpty())
            return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
