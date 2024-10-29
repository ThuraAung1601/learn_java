import java.util.*;

class Graph {
    private final Map<Integer, List<int[]>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    // Adds an edge from vertex u to v with the given weight
    public void addEdge(int u, int v, int weight) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, weight});
        adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, weight});  // For undirected graphs
    }

    public void dijkstra(int startVertex) {
        // Initialize distances with "infinity" (Integer.MAX_VALUE) for all vertices except the start
        Map<Integer, Integer> distances = new HashMap<>();
        for (int vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);

        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < adjList.size() - 1; i++) {
            // Get the vertex with the minimum distance that hasn’t been visited
            int u = minDistance(distances, visited);
            visited.add(u);

            // Relaxation: update distances of adjacent vertices
            for (int[] neighbor : adjList.getOrDefault(u, new ArrayList<>())) {
                int v = neighbor[0];
                int weight = neighbor[1];

                if (!visited.contains(v) && distances.get(u) != Integer.MAX_VALUE &&
                    distances.get(u) + weight < distances.get(v)) {
                    distances.put(v, distances.get(u) + weight);
                }
            }
        }

        printDistances(distances, startVertex);
    }

    // Helper function to find the vertex with the minimum distance
    private int minDistance(Map<Integer, Integer> distances, Set<Integer> visited) {
        int minDistance = Integer.MAX_VALUE;
        int minVertex = -1;

        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            int vertex = entry.getKey();
            int distance = entry.getValue();
            if (!visited.contains(vertex) && distance < minDistance) {
                minDistance = distance;
                minVertex = vertex;
            }
        }
        return minVertex;
    }

    private void printDistances(Map<Integer, Integer> distances, int startVertex) {
        System.out.println("Shortest distances from vertex " + startVertex + ":");
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            System.out.println("Vertex " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}

public class DijkstraWithHashMap {
    public static void main(String[] args) {
        Graph graph = new Graph();

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
