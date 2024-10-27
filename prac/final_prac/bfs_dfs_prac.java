import java.util.*;

class Graph {
    Map<Integer, List<Integer>> adjList = new HashMap<>(); 
    
    Graph(int[] vertices, int[][] edges) {
        for(int v: vertices) {
            adjList.put(v, new ArrayList<>());
        }
        for(int[] e: edges) {
            addEdge(e[0], e[1]);
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        // adjList.get(v).add(u); // uncomment for undirected graph
    }

    public void bfs(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> Q = new LinkedList<>();

        visited.add(startVertex);
        Q.add(startVertex);

        while(!Q.isEmpty()) {
            int u = Q.poll();
            System.out.print(u + " ");
            for(int neighbor: adjList.get(u)) {
                if(!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    Q.add(neighbor);
                }
            }
        }
        System.out.println(); // For new line after BFS output
    }

    public void dfs(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(startVertex);
        while(!stack.isEmpty()) {
            int u = stack.pop();
            if(!visited.contains(u)) {
                System.out.print(u + " ");
                visited.add(u);

                for(int neighbor: adjList.get(u)) {
                    if(!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.println(); // For new line after DFS output
    }
}

public class bfs_dfs_prac {
    public static void main(String[] args) {
        int[] vertices = {1, 2, 3, 4, 5};
        int[][] edges = {
            {1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 5}
        };

        Graph graph = new Graph(vertices, edges);

        System.out.println("BFS Traversal starting from vertex 1:");
        graph.bfs(1); // Expected output: 1 2 3 4 5 (order may vary)

        System.out.println("DFS Traversal starting from vertex 1:");
        graph.dfs(1); // Expected output: 1 2 4 5 3 (order may vary)
    }
}
