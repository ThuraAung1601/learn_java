package solution;

import java.util.PriorityQueue;

public class NetworkOptimizer {

    public int optimizeNetwork(int N, int M, int[][] input) {
        int totalLatency = 0;

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i + 1);
        }

        for (int i = 0; i < input.length; i++) {
            int node1Id = input[i][0]; //start node
            int node2Id = input[i][1]; //end node
            int latency = input[i][2]; //latency
            Edge edge = new Edge(latency, nodes[node1Id - 1], nodes[node2Id - 1]);
            nodes[node1Id - 1].addEdge(edge);
            nodes[node2Id - 1].addEdge(edge);
        }

        /*
         * TASK 2: Implement PRIM's algorithm to find the minimum spanning tree
         * by using latency as the weight
         * 
         * The algorithm should return the total latency of the minimum spanning tree
         */

        boolean selected[] = new boolean[N];
        Node startNode = nodes[0];
        selected[0] = true;
        PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeComparator());

        // implement the algorithm here

        pq.addAll(startNode.getEdges());
        while (!pq.isEmpty() && countSelectedNode(selected) < N) { //loop until pq is not empty and all nodes(N - 1) are visited
            
                Edge curEdge = pq.poll();

                //to find the connected node from each side
                Node nextNode = !selected[curEdge.getFrom().getId() - 1] ? curEdge.getFrom() : curEdge.getTo();

                if(selected[nextNode.getId() - 1]){
                    continue; //skip if that node is already visited
                }

                selected[nextNode.getId() - 1] = true; //set true if it is not visited
                totalLatency += curEdge.getLatency();
                pq.addAll(nextNode.getEdges());  //add all edge of new Node to pq
    
        }
        if(countSelectedNode(selected) == N){
            return totalLatency;
        }
        return -1;
        // return totalLatency;

    }

    // Overload the optimizeNetwork method
    public int optimizeNetwork(int N, int M, int T, int[][] input) {
        int totalLatency = 0;

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i + 1);
        }

        for (int i = 0; i < input.length; i++) {
            int node1Id = input[i][0];
            int node2Id = input[i][1];
            int latency = input[i][2];
            int bandwidth = input[i][3];
            

            if(bandwidth >= T){
                EdgeWithBandwidth edge = new EdgeWithBandwidth(bandwidth,latency,nodes[node1Id - 1], nodes[node2Id - 1]);
                nodes[node1Id - 1].addEdgeWithBandwidth(edge);
                nodes[node2Id - 1].addEdgeWithBandwidth(edge);
            }
        }

        /*
         * TASK 3: Implement PRIM's algorithm to find the minimum spanning tree
         * by using latency as the weight and
         * the edge that have bandwidth less than T should not be included in the
         * minimum spanning tree
         * 
         * The algorithm should return the total latency of the minimum spanning tree
         */

        boolean selected[] = new boolean[N];
        Node startNode = nodes[0];
        selected[0] = true;
        PriorityQueue<EdgeWithBandwidth> pq = new PriorityQueue<>(new EdgeBandwidthComparator());
        pq.addAll(startNode.getEdgesWithBandwidth());

        while (!pq.isEmpty() && countSelectedNode(selected) < N) {
            EdgeWithBandwidth curEdge = pq.poll();
            Node nextNode = !selected[curEdge.getFrom().getId() - 1] ? curEdge.getFrom() : curEdge.getTo();

            if(selected[nextNode.getId() - 1]){
                continue;
            }

            selected[nextNode.getId() - 1] = true;
            totalLatency += curEdge.getLatency();
            pq.addAll(nextNode.getEdgesWithBandwidth());


        }

        if(countSelectedNode(selected) == N){
            return totalLatency;
        }
        return -1;
    }

    private int countSelectedNode(boolean[] arr){
        int count = 0;
        for(boolean ele : arr){
            if(ele){
                count++;
            }
        }
        return count;
    }

}