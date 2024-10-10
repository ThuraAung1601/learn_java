package solutions.pack12b_SSP;
import java.util.Arrays;

public class Dijkstra_661606 {
  int[][] adjMatrix;
  int[] previous, distance;
  boolean[] visited;
  int source;

  public Dijkstra_661606(int[][] adjMatrix, int source) {
      this.adjMatrix = adjMatrix;
      this.source = source;
      previous = new int[adjMatrix.length];
      distance = new int[adjMatrix.length];
      visited = new boolean[adjMatrix.length];

      Arrays.fill(distance, Integer.MAX_VALUE); //Initialize distacnes to infinity
      distance[source] = 0;
      Arrays.fill(previous, -1);
      previous[source] = source;
  }
  
  //Task 1 imeplement the findSSP method using Dijkstra's algorithm
  public void findSSP() {
      int n = adjMatrix.length;

      for (int i = 0; i < n - 1; i++) {
          int u = selectMinVertex(); 
          visited[u] = true;

          for (int v = 0; v < n; v++) {
              if (!visited[v] && adjMatrix[u][v] != -1 && distance[u] != Integer.MAX_VALUE &&
                  distance[u] + adjMatrix[u][v] < distance[v]) {
                      distance[v] = distance[u] + adjMatrix[u][v];
                      previous[v] = u;
                  }
          }
      }

      System.out.println(Arrays.toString(distance));
      System.out.println(Arrays.toString(previous));
  }

  private int selectMinVertex() {
      int min = Integer.MAX_VALUE;
      int minIndex = -1;

      for (int i = 0; i < distance.length; i++) {
          if (!visited[i] && distance[i] < min){
              min = distance[i];
              minIndex = i;
          }
      }
      return minIndex;
  }

  //Task 2 implement the printAllSSP method to print the shortest path 
  //from the source to all other nodes
  public void printAllSSP(){
      for (int i = 0; i < adjMatrix.length; i++) {
          System.out.print("Shortest path from " + source + " to " + i + " is ");
  
          if (i == source) {
              System.out.println(i + "\nwith a distance of " + distance[i] + "\n");
          } else {
              printPathInline(i);
              System.out.println("\nwith a distance of " + distance[i] + "\n");
          }
      }
  }

  private void printPathInline(int node) {
      if (node == source) {
          System.out.print(source);
          return;
      }
      printPathInline(previous[node]);
      System.out.print(" -> " + node);
  }
}