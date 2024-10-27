public class recursion_prac {
    public static int factorial(int num) {
        if(num <= 1) {
            return 1;
        }
        return num * factorial(num-1);
    }
    public static int factorial(int num, int[] memo) {
        if(num <= 1) {
            return 1;
        }
        if(memo[num] != 0) {
            return memo[num];
        }
        memo[num] = num * factorial(num-1, memo);
        return memo[num];
    }
    public static int factorialDP(int num) {
        int[] dp = new int[num+1];
        dp[0] = 1; dp[1] = 1; // base case
        for(int i = 2; i <= num; i++) {
            dp[i] = i * dp[i-1];
        }
        return dp[num];
    }
    public static void main(String[] args) {
        int num = 5;
        int[] memo = new int[num+1];
        System.out.println(factorial(num));
        System.out.println(factorialDP(num));
        System.out.println(factorial(num, memo));
    }
}
