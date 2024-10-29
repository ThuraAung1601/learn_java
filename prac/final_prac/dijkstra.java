import java.util.*;

class MinHeap {
    int MAX_SIZE = 100;
    int size = 0;
    int[] heap = new int[MAX_SIZE];

    MinHeap() {}

    public void insert(int data) {
        if (isEmpty()) {
            heap[0] = data;
        }
        int i = size++;
        heap[i] = data;
        int parent = (i - 1) / 2;
        while (i > 0 && heap[i] < heap[parent]) {
            int temp = heap[parent];
            heap[parent] = heap[i];
            heap[i] = temp;

            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public int remove() {
        int removed = heap[0];
        heap[0] = heap[--size];
        int current = 0;
        while (true) {
            int smallest = current;
            int left = 2 * current + 1;
            int right = 2 * current + 2;

            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }
            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }

            int temp = heap[current];
            heap[current] = heap[smallest];
            heap[smallest] = temp;

            if (current == smallest) {
                break;
            }

            current = smallest;
        }

        return removed;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int peek() {
        return heap[0];
    }

    void printMinHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}

class WGraph {
    int[][] adjMatrix;
    int numVertices;

    public WGraph(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices];

        // Initialize the adjacency matrix with a large value (infinity)
        for (int[] i : adjMatrix) {
            Arrays.fill(i, Integer.MAX_VALUE);
        }
    }

    public void addEdge(int from, int to, int weight) {
        adjMatrix[from][to] = weight;
    }

    public void dijkstra(int startVertex) {
        int[] distances = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        int[] predecessors = new int[numVertices]; // To keep track of the path
        Arrays.fill(predecessors, -1); // Initialize predecessors to -1

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        MinHeap heap = new MinHeap();
        heap.insert(startVertex);

        while (!heap.isEmpty()) {
            int u = heap.remove();

            if (visited[u]) continue;
            visited[u] = true;

            for (int v = 0; v < numVertices; v++) {
                // Check connection and check not visited
                if (adjMatrix[u][v] != Integer.MAX_VALUE && !visited[v]) {
                    // Corrected distance calculation
                    if (distances[u] + adjMatrix[u][v] < distances[v]) {
                        distances[v] = distances[u] + adjMatrix[u][v]; // Correctly update distance
                        predecessors[v] = u; // Update predecessor
                        heap.insert(v); // Insert the vertex into the heap
                    }
                }
            }
        }

        printDistances(distances, startVertex);
        // Example of printing the shortest path from startVertex to a specific vertex
        int targetVertex = 3; // Change this to test other vertices
        printShortestPath(predecessors, distances, startVertex, targetVertex);
    }

    private void printDistances(int[] distances, int startVertex) {
        System.out.println("Shortest distances from vertex " + startVertex + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Vertex " + i + " -> " +
                (distances[i] == Integer.MAX_VALUE ? "Infinity" : distances[i]));
        }
    }

    private void printShortestPath(int[] predecessors, int[] distances, int startVertex, int targetVertex) {
        if (distances[targetVertex] == Integer.MAX_VALUE) {
            System.out.println("No path from vertex " + startVertex + " to vertex " + targetVertex);
            return;
        }

        System.out.print("Shortest path from " + startVertex + " to " + targetVertex + ": ");
        List<Integer> path = new ArrayList<>();
        for (int at = targetVertex; at != -1; at = predecessors[at]) {
            path.add(at);
        }
        Collections.reverse(path); // Reverse the path to show it from start to target

        for (int vertex : path) {
            System.out.print(vertex + (vertex == targetVertex ? "" : " -> "));
        }
        System.out.println();
    }
}

public class dijkstra {
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
