import java.util.ArrayList;
import java.util.List;

class Graph {
    int[] V;
    List<int[]> E;

    Graph(int[] vertices, int[][] edges) {
        V = vertices;
        E = new ArrayList<>();
        for(int[] edge: edges) {
            E.add(edge);
        }
    }

    public int getDegree(int v) {
        int degree = 0;
        for(int[] e: E) {
            if(v == e[0] || v == e[1]) {
                degree++;
            }
        }
        return degree;
    }

    public int[] getAdjacentV(int v) {
        int[] adj = new int[getDegree(v)];
        int i = 0;
        for (int[] e : E) {
            if (v == e[0]) {
                adj[i] = e[1];
                i++;
            } else if (v == e[1]) {
                adj[i] = e[0];
                i++;
            }
        }
        return adj;
    }

    public void addEdge(int u, int v) {
        E.add(new int[]{u , v});
    }
}

public class graph_prac2 {
    
}
