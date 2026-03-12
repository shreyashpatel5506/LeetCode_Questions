import java.util.*;

public class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        k--;

        for (int i = 1; i <= n; i++) {

            int factorialSize = factorial(n - i);

            int index = k / factorialSize;
            sb.append(numbers.get(index));
            numbers.remove(index);
            k %= factorialSize;
        }

        return sb.toString();
    }
    private int factorial(int num) {
        int res = 1;
        for (int i = 2; i <= num; i++) {
            res *= i;
        }
        return res;
    }
}