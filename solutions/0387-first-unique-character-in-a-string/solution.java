class Solution {
    public int firstUniqChar(String s) {
      HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        char[] sArr = s.toCharArray();
        for(int i=0; i < sArr.length;i++){
            if(freqMap.get(sArr[i]) == 1){
                return i;
            }
        }

        return -1;
    }
}
