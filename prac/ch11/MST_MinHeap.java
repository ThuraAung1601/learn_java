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

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class MinHeapEdge {
    MinHeap heap;
    Edge[] edgeArray;
    int size;

    MinHeapEdge(int capacity) {
        heap = new MinHeap();
        edgeArray = new Edge[capacity];
        size = 0;
    }

    public void insert(Edge edge) {
        heap.insert(edge.weight);
        edgeArray[size++] = edge;
    }

    public Edge removeMin() {
        int minWeight = heap.remove();
        for (int i = 0; i < size; i++) {
            if (edgeArray[i].weight == minWeight) {
                Edge minEdge = edgeArray[i];
                edgeArray[i] = edgeArray[--size];
                return minEdge;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}

class Graph {
    int V;
    int[][] adjMatrix;

    Graph(int V) {
        this.V = V;
        adjMatrix = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                adjMatrix[i][j] = Integer.MAX_VALUE; // Initialize with max value
            }
        }
    }

    void addEdge(int src, int dest, int weight) {
        adjMatrix[src][dest] = weight;
        adjMatrix[dest][src] = weight;
    }

    void primMST() {
        boolean[] inMST = new boolean[V];
        int[] key = new int[V]; // Used to pick minimum weight edge
        Edge[] result = new Edge[V]; // Stores the MST edges

        // Initialize all keys to infinity (or maximum value)
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
        }

        MinHeapEdge minHeapEdge = new MinHeapEdge(V);

        int start = 0; // Start from the 0th vertex
        key[start] = 0;
        inMST[start] = true;

        // Add all edges from the starting vertex
        for (int v = 0; v < V; v++) {
            if (adjMatrix[start][v] != Integer.MAX_VALUE && v != start) {
                minHeapEdge.insert(new Edge(start, v, adjMatrix[start][v]));
            }
        }

        System.out.println("Edges in the Minimum Spanning Tree (MST):");

        while (!minHeapEdge.isEmpty()) {
            Edge minEdge = minHeapEdge.removeMin();

            if (inMST[minEdge.dest]) {
                continue; // Skip if vertex is already in MST
            }

            inMST[minEdge.dest] = true;
            result[minEdge.dest] = minEdge;

            for (int v = 0; v < V; v++) {
                if (adjMatrix[minEdge.dest][v] != Integer.MAX_VALUE && !inMST[v]) {
                    minHeapEdge.insert(new Edge(minEdge.dest, v, adjMatrix[minEdge.dest][v]));
                }
            }
        }

        int mstWeight = 0;
        for (int i = 1; i < V; i++) { // Skip vertex 0 as it has no parent in the MST
            System.out.println(result[i].src + " - " + result[i].dest + " (Weight: " + result[i].weight + ")");
            mstWeight += result[i].weight;
        }
        System.out.println("Total weight of the MST: " + mstWeight);
    }
}

public class MST_MinHeap {
    public static void main(String[] args) {
        int V = 5;
        Graph graph = new Graph(V);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        graph.primMST();
    }
}
