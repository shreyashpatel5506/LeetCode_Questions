class Solution {
    public int totalWaviness(int num1, int num2) {
        int totalWaviee = 0;
        for(int i=num1;i<=num2;i++){
            int wavies = countWavieness(i);
            totalWaviee += wavies;
        }
        return totalWaviee;
    }
public int countWavieness(int num) {
    int[] numArr = convertnumarr(num);
    int wavie = 0;
    
    for (int i = 1; i < numArr.length - 1; i++) {
        if (numArr[i-1] < numArr[i] && numArr[i+1] < numArr[i]) {
            wavie++;
        }
        else if (numArr[i-1] > numArr[i] && numArr[i+1] > numArr[i]) {
            wavie++;
        }
    }
    return wavie;
}

public int[] convertnumarr(int n) {
   
    int number = Math.abs(n); 
    
    int length = Integer.toString(number).length();
    int[] arr = new int[length];

    for (int i = length - 1; i >= 0; i--) {
        arr[i] = number % 10;
        number = number / 10;
    }

    return arr;
}
}
