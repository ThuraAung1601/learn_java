import java.util.*;

class Graph {
    private final int vertices;
    private final List<List<int[]>> adjList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Add an edge from vertex u to v with given weight
    public void addEdge(int u, int v, int weight) {
        adjList.get(u).add(new int[]{v, weight});
        // adjList.get(v).add(new int[]{u, weight});  // For undirected graphs; remove for directed
    }

    public void dijkstra(int startVertex) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices - 1; i++) {
            int u = minDistance(distances, visited);
            visited[u] = true;

            // Relax edges for the current vertex
            for (int[] neighbor : adjList.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];

                if (!visited[v] && distances[u] != Integer.MAX_VALUE &&
                    distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                }
            }
        }

        printDistances(distances, startVertex);
    }

    // Helper function to find the vertex with the minimum distance
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

public class DijkstraWithoutPQ {
    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 4, 5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 2, 6);

        graph.dijkstra(0);   // Find shortest paths from vertex 0
    }
}
