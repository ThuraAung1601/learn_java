public class recursion_prac {
    public static int factorial(int num) {
        if(num == 0 || num == 1) {
            return 1;
        }
        return num * factorial(num-1);
    }
    public static int factorial_memo(int num, int[] memo) {
        if(num == 0 || num == 1) {
            return 1;
        } 
        if(memo[num] != 0) {
            return memo[num];
        }
        memo[num] = num * factorial_memo(num-1, memo);
        return memo[num];
    }
    public static int factorial_dp(int num) {
        if (num == 0 || num == 1) {
            return 1;
        }
        // +1 is for 0th index
        int[] dp = new int[num+1];
        dp[0] = 1; dp[1] = 1;
        for(int i = 2; i <= num; i++) {
            dp[i] = i * dp[i-1];
        }
        return dp[num];
    }

    public static int fibbo(int num) {
        if(num == 0 || num == 1) {
            return num;
        }
        return fibbo(num-1) + fibbo(num-2);
    }

    public static int fibbo_memo(int num, int[] memo) {
        if(num == 0 || num == 1) {
            return num;
        } 
        if(memo[num] != 0) {
            return memo[num];
        }
        memo[num] = fibbo_memo(num-1, memo) + fibbo_memo(num-2, memo);
        return memo[num];
    }

    public static int fibbo_dp(int num) {
        if(num == 0 || num == 1) {
            return num;
        }
        int[] dp = new int[num+1];
        dp[0] = 0; dp[1] = 1;

        for(int i = 2; i <= num; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[num];
    }

    public static int stair(int steps) {
        if (steps == 0 || steps == 1) {
            return 1;
        } 
        return stair(steps-1) + stair(steps-2);
    }

    public static int stair_memo(int steps, int[] memo) {
        if(steps == 0 || steps == 1) {
            return 1;
        }
        if(memo[steps] != 0) {
            return memo[steps];
        }
        memo[steps] = stair_memo(steps-1, memo) + stair_memo(steps-2, memo);
        return memo[steps];
    }

    public static int stair_dp(int steps) {
        if(steps == 0 || steps == 1) {
            return 1;
        }
        int[] dp = new int[steps+1];
        dp[0] = 1; dp[1] = 1;
        for(int i = 2; i <= steps; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[steps];
    }

    public static void main(String args[]) {
        int num = 5;
        int[] memo = new int[num+1];
        System.out.println("Factorial Recursion: " + factorial(num));
        System.out.println("Factorial with memo: " + factorial_memo(num, memo));
        System.out.println("Factorial DP: " + factorial_dp(num));

        int[] memo2 = new int[num+1];
        System.out.println("Fibbonaci: " + fibbo(num));
        System.out.println("Fibbonaci: " + fibbo_memo(num, memo2));
        System.out.println("Fibbonaci: " + fibbo_dp(num));

        int[] memo3 = new int[num+1];
        System.out.println("Stairs: " + stair(num));
        System.out.println("Stairs memo: " + stair_memo(num, memo3));
        System.out.println("Stairs dp: " + stair_dp(num));

    }
}