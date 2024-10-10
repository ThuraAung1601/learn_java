package solutions.pack9_Heap;

import java.util.ArrayList;

public class MyHeapSort_661606 {
    private ArrayList<Integer> heap;
    
    public MyHeapSort_661606() {
        heap = new ArrayList<>();
    }

    // Insert an element into the heap
    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    // Sort the elements using heap sort
    public ArrayList<Integer> sort() {
        ArrayList<Integer> sortedList = new ArrayList<>(heap);
        int size = sortedList.size();

        // Build the max heap
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapifyDown(sortedList, i, size);
        }

        // Extract elements one by one from the heap
        for (int i = size - 1; i > 0; i--) {
            // Move current root to the end
            int temp = sortedList.get(0);
            sortedList.set(0, sortedList.get(i));
            sortedList.set(i, temp);

            // Call heapify on the reduced heap
            heapifyDown(sortedList, 0, i);
        }

        return sortedList;
    }

    // Heapify the element at the given index upwards
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) > heap.get(parentIndex)) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Heapify the element at the given index downwards
    private void heapifyDown(ArrayList<Integer> heap, int index, int size) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size && heap.get(left) > heap.get(largest)) {
            largest = left;
        }

        if (right < size && heap.get(right) > heap.get(largest)) {
            largest = right;
        }

        if (largest != index) {
            swap(heap, index, largest);
            heapifyDown(heap, largest, size);
        }
    }

    // Swap two elements in the heap
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Overloaded swap function for sort method
    private void swap(ArrayList<Integer> heap, int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
