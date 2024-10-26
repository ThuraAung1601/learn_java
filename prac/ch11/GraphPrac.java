class QueueArr {
    // First in First out
    int MAX_SIZE = 100;
    int[] data = new int[MAX_SIZE];
    int head = 0; int tail = 0;

    public void enqueue(int num) {
        if(isFull()) {
            data = expand(2);
        }
        data[tail] = num;
        tail = (tail+1)%MAX_SIZE;
    }

    public int dequeue() {
        int front = data[head];
        head = (head+1)%MAX_SIZE;
        return front;
    }

    public int front() {
        return data[head];
    }

    public boolean isFull() {
        int temp = (tail+1)%MAX_SIZE;
        return temp == head;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        if (tail >= head) {
            return tail - head;
        }
        return MAX_SIZE - head + tail;
    }

    public void clear() {
        // pointers reset
        head = 0; tail = 0;
    }

    // last element - tail
    public int rear() {
        if(isEmpty()) {
            return -1;
        } else {
            int i = (tail - 1 + MAX_SIZE) % MAX_SIZE; 
            return data[i];
        }
    }

    public boolean isIn(int num) {
        int i = head;
        while(i != tail) {
            if(data[i] == num) {
                return true;
            }
            i = (i+1)%MAX_SIZE;
        }
        return false;
    }

    public int[] expand(int k) {
        int newSize = MAX_SIZE * k;
        int[] newData = new int[newSize];
    
        // Copy elements from the current circular queue
        if (tail >= head) {
            for (int i = head; i < tail; i++) {
                newData[i - head] = data[i];
            }
            tail = tail - head;
        } else {
            int i = 0;
            for (int j = head; j < MAX_SIZE; j++, i++) {
                newData[i] = data[j];
            }
            for (int j = 0; j < tail; j++, i++) {
                newData[i] = data[j];
            }
            tail = i;
        }
        
        head = 0; // Reset head to 0 after copying
        MAX_SIZE = newSize; // Update the size of the queue
        return newData;
    }    

    @Override
    public String toString() {
        if(isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("head->");
        int i = head;
        while(i != tail) {
            sb.append(data[i] + "->");
            i = (i+1)%MAX_SIZE;
        }
        sb.append("tail");
        return sb.toString();
    }
}

class Node {
    int value;
    Node next;

    public Node(int v) {
        value = v;
    }
}

class Edge {
    int src, dest, weight;

    Edge(int source, int destination) {
        src = source;
        dest = destination;
        weight = 1; // Default weight for unweighted graph
    }

    Edge(int source, int destination, int w) {
        src = source;
        dest = destination;
        weight = w;
    }
}

class Graph {
    private int[][] adjMatrix;
    private int vertices; // Number of vertices

    Graph(int V) {
        vertices = V;
        adjMatrix = new int[vertices][vertices];
    }

    // Add an edge to an unweighted graph
    public void addEdge(int src, int dest) {
        Edge edge = new Edge(src, dest);
        adjMatrix[edge.src][edge.dest] = edge.weight;

        // Uncomment this line if the graph is undirected
        // adjMatrix[edge.dest][edge.src] = edge.weight;
    }

    public void displayGraph() {
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void bfs(int start) {
        QueueArr q = new QueueArr();
        boolean[] visited = new boolean[vertices];
        visited[start] = true;
        q.enqueue(start);

        while(!q.isEmpty()) {
            int v = q.dequeue();
            System.out.print(v + " ");

            for(int i = 0; i < vertices; i++) {
                if(adjMatrix[v][i] == 1 && !visited[v]) {
                    q.enqueue(i);
                    visited[v] = true;
                }
            }
            System.out.println();
        }
    }

    private void dfsRecursion(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        for (int i = 0; i < vertices; i++) {
            if(adjMatrix[v][i] == 1 && !visited[v]) {
                dfsRecursion(i, visited);
            }
        }
    }

    public void dfs(int start) {
        boolean[] visited = new boolean[vertices];
        dfsRecursion(start, visited);
        System.out.println();
    }
}

public class GraphPrac {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        graph.displayGraph();
    }
}
