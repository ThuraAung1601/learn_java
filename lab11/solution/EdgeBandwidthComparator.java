package solution;
import java.util.Comparator;
public class EdgeBandwidthComparator implements Comparator<EdgeWithBandwidth> {
    @Override
    public int compare(EdgeWithBandwidth e1, EdgeWithBandwidth e2){
        return Integer.compare(e1.getLatency(), e2.getLatency());
    }
}   
