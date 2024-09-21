
class Solution:
    def isPalindrome(self, head: Optional[ListNode]) -> bool:
        list = [];
        while head :
            list.append(head.val)
            head =head.next
        left = 0
        right = len(list) -1
        while left<right and list[left] == list[right] :
            left = left +1
            right =right - 1 
        return left >= right
