class Solution:
    def nextGreatestLetter(self, letters: List[str], target: str) -> str:
        target_code = ord(target)
        greater_letters = []

        for letter in letters:
            if ord(letter) > target_code:
                greater_letters.append(letter)
  
        if len(greater_letters)==0:
            return letters[0]

        return min(greater_letters)

