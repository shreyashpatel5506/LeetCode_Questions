class Solution:
    def reverse(self, x: int) -> int:

        

       
        if x < 0:
            reversed_str = str(x)[:0:-1] 
            result = -int(reversed_str)
        else:
            reversed_str = str(x)[::-1]
            result = int(reversed_str)
        
       
        if result <  -2**31 or result >  2**31 - 1:
            return 0

        return result

