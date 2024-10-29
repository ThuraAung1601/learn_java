import java.util.Arrays;

class MinHeap {
    int MAX_SIZE = 100;
    int size = 0;
    int[] heap = new int[MAX_SIZE];

    MinHeap() {}

    public void insert(int data) {
        int i = size++;
        heap[i] = data;
        int parent = (i - 1) / 2;
        // check up to 0th index - root
        while (i > 0 && heap[i] < heap[parent]) {
            // swap
            int temp = heap[i];
            heap[i] = heap[parent];
            heap[parent] = temp;

            i = parent; 
            parent = (i - 1) / 2;
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

            // swap with smallest
            int temp = heap[current];
            heap[current] = heap[smallest];
            heap[smallest] = temp;

            // assume current points to the smallest
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
    
    public void enqueue(int data) {
        minheap.insert(data);
    }

    public int dequeue() {
        return minheap.remove();
    }

    public int front() {
        return minheap.peek();
    }

    public boolean isEmpty() {
        return minheap.isEmpty();
    }

    @Override
    public String toString() {
        return minheap.toString();
    }
}

class WGraph {
    private int[][] adjMatrix; // Adjacency matrix
    private int numVertices;

    public WGraph(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices];
        
        // Initialize the adjacency matrix with a large value (infinity)
        for (int i = 0; i < numVertices; i++) {
            Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
        }
    }

    public void addEdge(int from, int to, int weight) {
        adjMatrix[from][to] = weight;
        adjMatrix[to][from] = weight; // For undirected graph
    }

    public void dijkstra(int startVertex) {
        int[] distances = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        // Initialize distances to "infinity"
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        // Initialize the priority queue (min-heap)
        pQueue pq = new pQueue();
        pq.enqueue(startVertex);

        while (!pq.isEmpty()) {
            // Get the vertex with the minimum distance
            int u = pq.dequeue();

            if (visited[u]) continue; // Skip if already visited
            visited[u] = true;

            // Update distances to adjacent vertices
            for (int v = 0; v < numVertices; v++) {
                // Check if there is an edge and if the vertex hasn't been visited
                if (adjMatrix[u][v] != Integer.MAX_VALUE && !visited[v]) {
                    // Relaxation step
                    if (distances[u] + adjMatrix[u][v] < distances[v]) {
                        distances[v] = distances[u] + adjMatrix[u][v];
                        pq.enqueue(v); // Enqueue the vertex with updated distance
                    }
                }
            }
        }

        printDistances(distances, startVertex);
    }

    private void printDistances(int[] distances, int startVertex) {
        System.out.println("Shortest distances from vertex " + startVertex + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Vertex " + i + " -> " + (distances[i] == Integer.MAX_VALUE ? "Infinity" : distances[i]));
        }
    }
}

public class DijkstraWithMinHeap {
    public static void main(String[] args) {
        WGraph graph = new WGraph(5); // Create a graph with 5 vertices

        // Adding edges with weights
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 4, 5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 2, 6);

        // Run Dijkstra's algorithm from vertex 0
        graph.dijkstra(0);
    }
}
