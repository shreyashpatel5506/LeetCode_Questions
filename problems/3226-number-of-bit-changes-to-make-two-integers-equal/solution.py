class Solution:
    def minChanges(self, n: int, k: int) -> int:
        count = 0
        while n > 0 or k > 0:
            n_bit = n % 2
            k_bit = k % 2
            if n_bit == 1 and k_bit == 0:
                count += 1
            if n_bit == 0 and k_bit == 1:
                return -1

            n = n // 2  # Use integer division
            k = k // 2  # Use integer division

        return count
