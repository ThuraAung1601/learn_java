import java.util.*;

class MinHeap {
    int MAX_SIZE = 100;
    int size = 0;
    int[] heap = new int[MAX_SIZE];
    int[] vertexMap = new int[MAX_SIZE]; // To track the vertex associated with the heap index

    MinHeap() {}

    public void insert(int data) {
        if (isEmpty()) {
            heap[0] = data;
            vertexMap[0] = data; // Track the vertex
        }
        int i = size++;
        heap[i] = data;
        vertexMap[i] = data; // Track the vertex
        int parent = (i - 1) / 2;
        while (i > 0 && heap[i] < heap[parent]) {
            int temp = heap[parent];
            heap[parent] = heap[i];
            heap[i] = temp;

            int tempVertex = vertexMap[parent];
            vertexMap[parent] = vertexMap[i];
            vertexMap[i] = tempVertex;

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

            if (smallest == current) {
                break;
            }

            int temp = heap[current];
            heap[current] = heap[smallest];
            heap[smallest] = temp;

            int tempVertex = vertexMap[current];
            vertexMap[current] = vertexMap[smallest];
            vertexMap[smallest] = tempVertex;

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
        adjMatrix[to][from] = weight; // For undirected graph
    }

    public void prim(int startVertex) {
        boolean[] inMST = new boolean[numVertices];
        int[] key = new int[numVertices];
        int[] parent = new int[numVertices];

        // Initialize keys to infinity and parent to -1
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        // Key of the start vertex is 0
        key[startVertex] = 0;

        MinHeap heap = new MinHeap();
        heap.insert(startVertex);

        while (!heap.isEmpty()) {
            int u = heap.remove(); // Vertex with the minimum key

            if (inMST[u]) continue; // If already in MST, skip
            inMST[u] = true; // Add vertex to MST

            // Check the adjacency matrix for connected vertices
            for (int v = 0; v < numVertices; v++) {
                // If there's an edge and v is not in MST
                if (adjMatrix[u][v] != Integer.MAX_VALUE && !inMST[v]) {
                    // Update key and parent if we find a smaller edge
                    if (adjMatrix[u][v] < key[v]) {
                        key[v] = adjMatrix[u][v];
                        parent[v] = u;
                        heap.insert(v); // Insert the updated vertex into the heap
                    }
                }
            }
        }

        printMST(parent);
    }

    private void printMST(int[] parent) {
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (int i = 1; i < parent.length; i++) {
            if (parent[i] != -1) {
                System.out.println(parent[i] + " - " + i);
            }
        }
    }
}

public class prim {
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

        // Run Prim's algorithm from vertex 0
        graph.prim(0);
    }
}
