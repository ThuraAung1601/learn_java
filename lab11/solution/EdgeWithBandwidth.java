package solution;

public class EdgeWithBandwidth {
    private int bandwidth;
    private Node from;
    private Node to;
    private int latency;  // Assuming we still care about latency for bandwidth edges

    public EdgeWithBandwidth(int bandwidth, int latency, Node from, Node to) {
        this.bandwidth = bandwidth;
        this.latency = latency;
        this.from = from;
        this.to = to;
    }

    public int getBandwidth() {
        return bandwidth;
    }

    public int getLatency() {
        return latency;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "EdgeWithBandwidth [bandwidth=" + bandwidth + ", latency=" + latency + "]";
    }
}

