class Solution:
    def tribonacci(self, n: int) -> int:
        tribonakki=[]
        tribonakki.append(0)
        tribonakki.append(1)
        tribonakki.append(1)
        for i in range(3,n+1) :
            tribonakki.append(tribonakki[i-1]+tribonakki[i-2]+tribonakki[i-3])
        
        return tribonakki[n]  
