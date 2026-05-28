class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int bestIndex = -1; 
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        TrieNode root = new TrieNode();
        int globalBestIdx = 0;
        for (int i = 1; i < wordsContainer.length; i++) {
            if (wordsContainer[i].length() < wordsContainer[globalBestIdx].length()) {
                globalBestIdx = i;
            }
        }
        root.bestIndex = globalBestIdx;

        for (int idx = 0; idx < wordsContainer.length; idx++) {
            String word = wordsContainer[idx];
            TrieNode curr = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                int charIdx = word.charAt(i) - 'a';
                if (curr.children[charIdx] == null) {
                    curr.children[charIdx] = new TrieNode();
                }
                curr = curr.children[charIdx];
                if (isBetter(idx, curr.bestIndex, wordsContainer)) {
                    curr.bestIndex = idx;
                }
            }
        }
        int[] ans = new int[wordsQuery.length];
        for (int q = 0; q < wordsQuery.length; q++) {
            String query = wordsQuery[q];
            TrieNode curr = root;
            int resIdx = root.bestIndex; 
            for (int i = query.length() - 1; i >= 0; i--) {
                int charIdx = query.charAt(i) - 'a';
                if (curr.children[charIdx] != null) {
                    curr = curr.children[charIdx];
                    resIdx = curr.bestIndex;
                } else {
                    break; 
                }
            }
            ans[q] = resIdx;
        }

        return ans;
    }
    private boolean isBetter(int currIdx, int standardIdx, String[] wordsContainer) {
        if (standardIdx == -1) return true;
        
        int lenCurr = wordsContainer[currIdx].length();
        int lenStandard = wordsContainer[standardIdx].length();
        
        if (lenCurr < lenStandard) return true;
        if (lenCurr == lenStandard && currIdx < standardIdx) return true;
        
        return false;
    }
}