import java.util.List;

public class Node {
    public int val;
    public List<Node> neightbors;

    public Node() { }

    public Node(int _val, List<Node> _neightbors){
        val = _val;
        neightbors = _neightbors;
    }
}
