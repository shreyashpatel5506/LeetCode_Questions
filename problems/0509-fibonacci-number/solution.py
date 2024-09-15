class Solution:
    def fib(self, N: int) -> int:
        
        if N < 0:
            return 0
        if N == 0:
            return 0
        if N == 1:
            return 1

        num1 = 0
        num2 = 1

        for i in range(2, N + 1):
            sum = num1 + num2
            num1 = num2
            num2 = sum
    
        return sum
