package solutions.pack12b_SSP;

import java.util.*;

public class Task3_661606 {

    // BFS to explore the state space and find the minimum number of operations
    public int stateSpace(int[] beginState, int[] goalState) {
        if (Arrays.equals(beginState, goalState)) {
            return 0;
        }

        // Lambda expression to swap adjacent pairs
        InterfaceSwapAdjacentPairs swapPairs = (State s) -> {
            int[] arr = s.getState().clone();
            for (int i = 0; i < arr.length - 1; i += 2) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
            return arr;
        };

        // Lambda expression to swap corresponding halves
        InterfaceSwapCorrespondingHalves swapHalves = (State s) -> {
            int[] arr = s.getState().clone();
            int mid = arr.length / 2;
            for (int i = 0; i < mid; i++) {
                int temp = arr[i];
                arr[i] = arr[i + mid];
                arr[i + mid] = temp;
            }
            return arr;
        };

        // BFS initialization
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Add the initial state to the queue
        queue.offer(new State(beginState, 0));
        visited.add(Arrays.toString(beginState));

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            int[] currentArray = currentState.getState();
            int currentOps = currentState.getOperations();

            // Apply the first operation (swap adjacent pairs)
            int[] newState1 = swapPairs.swapAdjacentPairs(currentState);
            if (Arrays.equals(newState1, goalState)) {
                return currentOps + 1; // Found the solution
            }
            if (visited.add(Arrays.toString(newState1))) {
                queue.offer(new State(newState1, currentOps + 1));
            }

            // Apply the second operation (swap corresponding halves)
            int[] newState2 = swapHalves.swapCorrespondingHalves(currentState);
            if (Arrays.equals(newState2, goalState)) {
                return currentOps + 1; // Found the solution
            }
            if (visited.add(Arrays.toString(newState2))) {
                queue.offer(new State(newState2, currentOps + 1));
            }
        }

        return -1; // If no solution is found
    }
}