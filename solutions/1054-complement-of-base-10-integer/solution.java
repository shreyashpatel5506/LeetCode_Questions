class Solution {
    public int bitwiseComplement(int n) {
   
        String binaryString = Integer.toBinaryString(n);
        char[] charArray = binaryString.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            
            if (charArray[i] == '0') {
                charArray[i] = '1';
            } else {
                charArray[i] = '0';
            }
        }

        String result = new String(charArray);
       
        return Integer.parseInt(result, 2);
    }
}
