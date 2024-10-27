import java.util.*;

class Graph {
    private final Map<Integer, List<Edge>> adjList = new HashMap<>();

    // Edge class to represent a connection between vertices
    private static class Edge {
        int target;
        int weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // Add vertices and edges to the graph
    public void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int from, int to, int weight) {
        adjList.putIfAbsent(from, new ArrayList<>());
        adjList.get(from).add(new Edge(to, weight));
    }

    // Dijkstra's algorithm to find the shortest path
    public Map<Integer, Integer> dijkstra(int start) {
        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        // Initialize distances
        for (int vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        minHeap.add(new Edge(start, 0));

        while (!minHeap.isEmpty()) {
            Edge current = minHeap.poll();
            int currentVertex = current.target;

            if (visited.contains(currentVertex)) {
                continue;
            }
            visited.add(currentVertex);

            // Update distances to neighbors
            for (Edge neighbor : adjList.get(currentVertex)) {
                if (!visited.contains(neighbor.target)) {
                    int newDist = distances.get(currentVertex) + neighbor.weight;
                    if (newDist < distances.get(neighbor.target)) {
                        distances.put(neighbor.target, newDist);
                        minHeap.add(new Edge(neighbor.target, newDist));
                    }
                }
            }
        }
        return distances;
    }
}

public class ssp_prac {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Add vertices and edges
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 2, 3);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 7);
        graph.addEdge(5, 1, 4);

        // Run Dijkstra's algorithm
        Map<Integer, Integer> distances = graph.dijkstra(1);

        // Print the shortest distances from vertex 1
        System.out.println("Shortest distances from vertex 1:");
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            System.out.println("Vertex " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
