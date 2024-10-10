package solution;

public class Edge {
    private int latency;
    private Node from;
    private Node to;

    public Edge(int latency, Node from, Node to){
        this.latency = latency;
        
        this.from = from;
        this.to = to;
    }
    public int getLatency(){
        return this.latency;
    }

    public Node getFrom(){
        return this.from;
    }

    public Node getTo(){
        return this.to;
    }

    public String toString() {
        return "Edge [latency=" + latency + "]";
    }
}

