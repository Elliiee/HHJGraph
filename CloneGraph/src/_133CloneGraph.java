import java.util.ArrayList;
import java.util.HashMap;

/*
Given a reference of a node in a connected undirected graph.
Return a deep copy (clone) of the graph.
 */
public class _133CloneGraph {
    private HashMap<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node){
        if (node == null) return node;

        //If the node was already visited before.
        //Return the clone from the visited dictionary.
        if (visited.containsKey(node))
            return visited.get(node);

        // Create a clone for the given node.
        // Note that we don't have cloned neighbors as of now, hence [].
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);

        for (Node neighbor : node.neightbors){
            cloneNode.neightbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }
}
