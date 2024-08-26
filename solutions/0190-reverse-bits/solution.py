class Solution:
    def reverseBits(self, n: int) -> int:
        binaryString=bin(n)[2:]
        binaryString=binaryString.zfill(32)
        reverse=binaryString[::-1]
        ans=int(reverse,2)
        return ans

