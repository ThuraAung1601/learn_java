class Graph {
    int[] vertices;
    int[][] edges; // {{from, to}, {from, to}}

    public Graph(int[] V, int[][] E) {
        vertices = V; edges = E;
    }
    public int getDegree(int v) {
        int degree = 0;
        for(int[] e: edges) {
            if(v == e[0] || v == e[1]) {
                degree++;
            }
        }
        return degree;
    }
    public int[] getAdjacentV(int v) {
        int[] adj = new int[getDegree(v)];
        int i = 0;
        for(int[] e: edges) {
            if(v == e[0]) {
                adj[i] = e[1];
                i++;
            } else if (v == e[1]) {
                adj[i] = e[0];
                i++;
            }
        }
        return adj;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("vertices: ");
        for(int i = 0; i < vertices.length; i++) {
            sb.append(vertices[i] + " ");
        }
        sb.append("\n");
        for(int i = 0; i < edges.length; i++) {
            sb.append("edge ");
            for(int j: edges[i]) {
                sb.append(j + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

public class graph_prac {
    public static void main(String[] args) {
        int[] V = {1, 2, 3, 4};
        int[][] E = {
            {1,2}, {2, 3}, {1, 4}
        };
        Graph g1 = new Graph(V, E);
        System.out.print(g1);
        System.out.println(g1.getDegree(2));
       int[] temp = g1.getAdjacentV(2);
       for(int i: temp) {
        System.out.print(i + " ");
       }
    }
}
