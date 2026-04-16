class Solution {
    public boolean canBeEqual(String s1, String s2) {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        if (a[0] != b[0]) {
            char temp = a[0];
            a[0] = a[2];
            a[2] = temp;
        }
        if (a[1] != b[1]) {
            char temp = a[1];
            a[1] = a[3];
            a[3] = temp;
        }
     
        return new String(a).equals(new String(b));
    }
}