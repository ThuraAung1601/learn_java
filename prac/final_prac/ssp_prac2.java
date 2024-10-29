import java.util.*;

class Graph {
    private final int vertices;
    private final int[][] adjMatrix;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjMatrix = new int[vertices][vertices];
        for (int[] row : adjMatrix) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
    }

    public void addEdge(int u, int v, int weight) {
        adjMatrix[u][v] = weight;
        adjMatrix[v][u] = weight;  // For undirected graphs; remove if directed
    }

    public void dijkstra(int startVertex) {
        int[] distances = new int[vertices];
        boolean[] visited = new boolean[vertices];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            int u = minDistance(distances, visited);

            visited[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && adjMatrix[u][v] != Integer.MAX_VALUE && 
                    distances[u] != Integer.MAX_VALUE && distances[u] + adjMatrix[u][v] < distances[v]) {
                    distances[v] = distances[u] + adjMatrix[u][v];
                }
            }
        }

        printDistances(distances, startVertex);
    }

    private int minDistance(int[] distances, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!visited[v] && distances[v] < minDistance) {
                minDistance = distances[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private void printDistances(int[] distances, int startVertex) {
        System.out.println("Shortest distances from vertex " + startVertex + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Vertex " + i + " -> " + distances[i]);
        }
    }
}

public class ssp_prac2 {
    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 4, 5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 2, 6);

        graph.dijkstra(0);  // Find shortest paths from vertex 0
    }
}
