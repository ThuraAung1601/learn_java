package Labs;

import java.util.ArrayList;
import java.util.List;
import solutions.pack7_Recursion.EqualSubsets_661606;
import solutions.pack7_Recursion.GridPaths_661606;
import solutions.pack7_Recursion.Subsets_661606;

public class Lab7_Recursion_DP_Main {
    public static void main(String[] args) {
        testEqualSubsets();
        // System.out.println("----");
        // testSubSets();
        // testGridPaths();
    }
    static void testEqualSubsets() {
        int [] a = {1,5,11,5};
        int [] b = {1,5,3};
        System.out.println(EqualSubsets_661606.canPartition_Recur(a));
        System.out.println(EqualSubsets_661606.canPartition_Recur(b));
        System.out.println(EqualSubsets_661606.canPartition_Memoiz(a));
        System.out.println(EqualSubsets_661606.canPartition_Memoiz(b));
        System.out.println(EqualSubsets_661606.canPartition_DP(a));
        System.out.println(EqualSubsets_661606.canPartition_DP(b));     
    }
    static void testSubSets() { 
        System.out.println("--- Subsets ---");
        List<Integer> set = new ArrayList<>();
        set.add(1); set.add(2); set.add(3);
        System.out.println("using recursive method");
        Subsets_661606.printSubsetsRecursive(set);
        System.out.println("using dynamic programming method");
        Subsets_661606.printSubsetsDP(set);
    }
    static void testGridPaths() {
        int [][] grid = { {0,0,0,0},
                          {0,1,0,0},  
                          {0,0,0,1},
                          {1,0,0,0}};
        System.out.println("--- grid paths ---");
        System.out.println("number of paths: " + GridPaths_661606.numberOfPaths(grid));
    }
}