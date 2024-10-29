import java.util.*;

class WGraph {
    List<Integer> vertices;
    List<int[]> edges; // Each edge is represented as {from, to, weight}
    Map<Integer, List<int[]>> adjList; // v -> List of [neighbour, weight]

    public WGraph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        adjList = new HashMap<>();
    }

    public void addEdge(int from, int to, int weight) {
        if (!vertices.contains(from)) {
            vertices.add(from);
        } 
        if (!vertices.contains(to)) {
            vertices.add(to);
        }

        // Check for the existence of the edge before adding it
        for (int[] edge : edges) {
            if ((edge[0] == from && edge[1] == to) || (edge[0] == to && edge[1] == from)) {
                // Edge already exists; you can update weight or skip addition
                return; // Skip if you want to prevent duplicates
            }
        }
        
        edges.add(new int[]{from, to, weight});
    }

    public void updateAdjList() {
        for (int v : vertices) {
            adjList.put(v, adjVertices(v));
        }
    }

    public List<int[]> adjVertices(int vertex) {
        List<int[]> adjV = new ArrayList<>();
        for (int[] e : edges) {
            if (vertex == e[0]) {
                adjV.add(new int[]{e[1], e[2]}); // add neighbour and weight
            } else if (vertex == e[1]) {
                adjV.add(new int[]{e[0], e[2]}); // add neighbour and weight
            }
        }
        return adjV;
    }

    public void dijkstra(int startVertex) {
        Map<Integer, Integer> distances = new HashMap<>();
        for (int vertex : vertices) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);

        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < vertices.size() - 1; i++) {
            int u = minDistance(distances, visited);
            visited.add(u);

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

public class DijkstraWithoutPQ {
    public static void main(String[] args) {
        WGraph graph = new WGraph();

        // Adding edges with weights
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 4, 5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 2, 6);
        
        // Attempt to add duplicate edges
        graph.addEdge(0, 1, 10); // This will not be added
        graph.addEdge(1, 2, 1); // This will not be added

        // Update adjacency list
        graph.updateAdjList();

        // Run Dijkstra's algorithm from vertex 0
        graph.dijkstra(0);
    }
}
