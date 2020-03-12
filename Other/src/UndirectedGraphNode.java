import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphNode {
    public int val;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(){
        val = 0;
        neighbors = new ArrayList<>();
    }

    public UndirectedGraphNode(int v){
        val = v;
        neighbors = new ArrayList<>();
    }

    public UndirectedGraphNode(int v, ArrayList<UndirectedGraphNode> n){
        val = v;
        neighbors = n;
    }
}
