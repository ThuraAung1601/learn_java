import java.util.ArrayList;
import java.util.List;

public class MergeK {

    // Custom min-heap class
    static class MinHeap {
        private final List<int[]> heap;

        public MinHeap() {
            heap = new ArrayList<>();
        }

        // Insert element in the heap
        public void offer(int[] element) {
            heap.add(element);
            int current = heap.size() - 1;
            int parent = (current - 1) / 2;
            while (current > 0 && heap.get(current)[0] < heap.get(parent)[0]) {
                swap(current, parent);
                current = parent;
                parent = (current - 1) / 2;
            }
        }

        // Remove and return the smallest element in the heap
        public int[] poll() {
            if (heap.isEmpty()) return null;
            int[] min = heap.get(0);
            int last = heap.size() - 1;
            heap.set(0, heap.get(last));
            heap.remove(last);
            heapify(0);
            return min;
        }

        // Heapify down from a given index
        private void heapify(int index) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < heap.size() && heap.get(left)[0] < heap.get(smallest)[0]) {
                smallest = left;
            }

            if (right < heap.size() && heap.get(right)[0] < heap.get(smallest)[0]) {
                smallest = right;
            }

            if (smallest != index) {
                swap(index, smallest);
                heapify(smallest);
            }
        }

        // Check if the heap is empty
        public boolean isEmpty() {
            return heap.isEmpty();
        }

        // Helper method to swap elements in the heap
        private void swap(int i, int j) {
            int[] temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }

    // Method to merge k sorted lists
    public static List<Integer> mergeKLists(List<List<Integer>> lists) {
        List<Integer> result = new ArrayList<>();
        MinHeap minHeap = new MinHeap();

        // Initialize the heap with the first element of each list
        for (int i = 0; i < lists.size(); i++) {
            if (!lists.get(i).isEmpty()) {
                minHeap.offer(new int[] {lists.get(i).get(0), i, 0});
            }
        }

        // Process the heap
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int value = current[0];
            int listIndex = current[1];
            int elementIndex = current[2];

            // Add the smallest value to the result
            result.add(value);

            // If the current list has more elements, add the next element to the heap
            if (elementIndex + 1 < lists.get(listIndex).size()) {
                minHeap.offer(new int[] {lists.get(listIndex).get(elementIndex + 1), listIndex, elementIndex + 1});
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example input
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(List.of(1, 4, 5));
        lists.add(List.of(1, 3, 4));
        lists.add(List.of(2, 6));

        List<Integer> mergedList = mergeKLists(lists);
        System.out.println(mergedList);  // Output: [1, 1, 2, 3, 4, 4, 5, 6]
    }
}
