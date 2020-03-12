import java.util.ArrayList;
import java.util.List;

public class Node {
    int val;
    List<Node> neighbors;

    public Node(){
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int v){
        val = v;
        neighbors = new ArrayList<>();
    }

    public Node(int v, ArrayList<Node> n){
        val = v;
        neighbors = n;
    }
}
