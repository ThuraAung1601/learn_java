package solutions.pack7_Recursion;

import java.util.ArrayList;
import java.util.List;

public class Subsets_661606 {

    public static void printSubsetsRecursive(List<Integer> set) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        collectSubsetsRecursive(set, 0, new ArrayList<>(), allSubsets);

        for (int i = 0; i < allSubsets.size(); i++) {
            System.out.print(allSubsets.get(i));
            if (i < allSubsets.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    // Helper method to collect all subsets
    private static void collectSubsetsRecursive(List<Integer> set, int index, List<Integer> subset, List<List<Integer>> allSubsets) {
        if (index == set.size()) {
            allSubsets.add(new ArrayList<>(subset));
            return;
        }

        // Include the current element and recurse
        subset.add(set.get(index));
        collectSubsetsRecursive(set, index + 1, subset, allSubsets);

        // Backtrack and exclude the current element, then recurse
        subset.remove(subset.size() - 1);
        collectSubsetsRecursive(set, index + 1, subset, allSubsets);
    }

    // Print all subsets of the given set using dynamic programming
    public static void printSubsetsDP(List<Integer> set) {
        int n = set.size();
        int totalSubsets = 1 << n; // 2^n subsets
        
        for (int i = 0; i < totalSubsets; i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // Check if j-th bit is set in the binary representation of i
                if ((i & (1 << j)) != 0) {
                    subset.add(set.get(j));
                }
            }
            System.out.print(subset);
            if (i < totalSubsets - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(); // Move to the next line after printing all subsets
    }
}
