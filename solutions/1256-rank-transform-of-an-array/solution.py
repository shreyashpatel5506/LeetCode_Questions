class Solution:
    def arrayRankTransform(self, arr: List[int]) -> List[int]:
        rank = {}
        sort_arr =sorted(list(set(arr)))

        for i in range(len(sort_arr)):
            rank[sort_arr[i]] = i+1

        for i in range(len(arr)):
            arr[i]=rank[arr[i]]

        return arr
