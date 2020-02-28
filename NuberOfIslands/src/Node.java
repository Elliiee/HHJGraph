import java.util.List;

public class Node {
    public int val;
    public List<Node> neighbors;

    public Node () {}

    public Node (int x, List<Node> n){
        this.val = x;
        this.neighbors = n;
    }
}
