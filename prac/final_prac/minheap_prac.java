class MinHeap {
    int MAX_SIZE = 100;
    int size = 0;
    int[] heap = new int[MAX_SIZE];

    MinHeap() {}

    public void insert(int data) {
        int i = size++;
        heap[i] = data;
        int parent = (i-1)/2;
        // check up to 0th index - root
        while(i > 0 && heap[i] < heap[parent]) {
            // swap
            int temp = heap[i];
            heap[i] = heap[parent];
            heap[parent] = temp;

            i = parent; 
            parent = (i-1)/2;
        }
    }

    public int remove() {
        int removed = heap[0];
        heap[0] = heap[--size];

        int current = 0;
        while (true) { 
            int left = 2 * current + 1;
            int right = 2 * current + 2;
            int smallest = current;

            // down to last index - size
            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }
            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }

            // current is actually smallest
            if (smallest == current) {
                break;
            }

            // the element in smallest - maybe left or right 
            int temp = heap[current];
            heap[current] = heap[smallest];
            heap[smallest] = temp;

            // assume current points the smallest
            current = smallest;
        }
        return removed;
    }

    public int peek() {
        return heap[0];
    }

    public boolean isFull() {
        return size == MAX_SIZE;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void heapSort(int[] arr) {
        for(int i : arr) {
            insert(i);
        }
        for(int i = 0; i < arr.length; i++) {
            arr[i] = remove();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Heap: ");
        for (int i = 0; i < size; i++) {
            sb.append(heap[i]); // Append the value at index i
            if (i < size - 1) {
                sb.append(", "); // Add a comma between elements
            }
        }
        return sb.toString();
    }
}

class pQueue {
    MinHeap minheap = new MinHeap();
    pQueue() {}

    public void enqueue(int data) {
        minheap.insert(data);
    }

    public int dequeue() {
        return minheap.remove();
    }

    public int front() {
        return minheap.peek();
    }

    @Override
    public String toString() {
        return minheap.toString();
    }
}

public class minheap_prac {

    public static void main(String args[]) {
        MinHeap h1 = new MinHeap();
        h1.insert(10);
        h1.insert(2);
        h1.insert(8);
        h1.insert(23);
        h1.insert(1);

        System.out.println(h1);

        h1.remove();
        System.out.println(h1);

        // Create a priority queue
        pQueue pq = new pQueue();

        // Enqueue elements
        pq.enqueue(10);
        pq.enqueue(2);
        pq.enqueue(8);
        pq.enqueue(23);
        pq.enqueue(1);

        // Print the priority queue
        System.out.println(pq);

        // Dequeue an element
        pq.dequeue();
        
        // Print the priority queue after dequeue
        System.out.println(pq);

        // Check the front element
        System.out.println("Front element: " + pq.front());
    }
}
