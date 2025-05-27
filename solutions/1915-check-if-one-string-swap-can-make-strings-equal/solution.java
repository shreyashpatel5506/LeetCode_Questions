class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        List<Integer> diff = new ArrayList<>();

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff.add(i);
                if (diff.size() > 2) return false;
            }
        }

        if (diff.size() == 0) return true;
        if (diff.size() == 2) {
            int i = diff.get(0), j = diff.get(1);
            return s1.charAt(i) == s2.charAt(j) && s1.charAt(j) == s2.charAt(i);
        }

        return false;
    }
}

