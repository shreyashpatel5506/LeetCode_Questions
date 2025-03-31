class Solution:
    def countBits(self, n: int) -> list[int]:
        def decimalToBinary(num: int) -> str:
            if num == 0:
                return "0"
            
            binary = []
            while num > 0:
                binary.insert(0, str(num % 2))  # Prepend binary digits
                num //= 2
            
            return "".join(binary)

        ans = [0] * (n + 1)
        
        for i in range(n + 1):
            bit = decimalToBinary(i)
            ans[i] = bit.count('1')  # Count '1's in the binary string
        
        return ans
