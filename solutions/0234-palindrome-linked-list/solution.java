class Solution {
    public boolean isPalindrome(ListNode head) {
        List<Integer> values = new ArrayList<>();

        // Convert linked list to array-like list
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }

       
        int start = 0;
        int end = values.size() - 1;
        int mid = start + ((end - start) / 2);
        
        for (int i = start, j = end; i <= mid && j > mid; i++, j--) {
            if (!values.get(i).equals(values.get(j))) {
                return false;
            }
        }

        return true;
    }
}
