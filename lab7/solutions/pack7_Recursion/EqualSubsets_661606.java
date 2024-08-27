package solutions.pack7_Recursion;

import java.util.Arrays;

public class EqualSubsets_661606 {

    public static boolean canPartition_Recur(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        if (sum % 2 != 0) return false;
        return canPartitionHelper(arr, sum / 2, 0);
    }

    private static boolean canPartitionHelper(int[] arr, int target, int index) {
        if (target == 0) return true;
        if (index >= arr.length || target < 0) return false;
        
        if (canPartitionHelper(arr, target - arr[index], index + 1)) return true;
        return canPartitionHelper(arr, target, index + 1);
    }

    public static boolean canPartition_Memoiz(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        if (sum % 2 != 0) return false;
        
        int target = sum / 2;
        Boolean[][] memo = new Boolean[arr.length][target + 1];
        return canPartitionHelperMemo(arr, target, 0, memo);
    }

    private static boolean canPartitionHelperMemo(int[] arr, int target, int index, Boolean[][] memo) {
        if (target == 0) return true;
        if (index >= arr.length || target < 0) return false;
        if (memo[index][target] != null) return memo[index][target];
        
        // Include the current element
        boolean include = canPartitionHelperMemo(arr, target - arr[index], index + 1, memo);
        // Exclude the current element
        boolean exclude = canPartitionHelperMemo(arr, target, index + 1, memo);
        
        memo[index][target] = include || exclude;
        return memo[index][target];
    }

    public static boolean canPartition_DP(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        if (sum % 2 != 0) return false;

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : arr) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[target];
    }
}
