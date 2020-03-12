import java.util.*;

public class CloneGraph {
    public Node cloneGraph(Node node){
        if (node == null) return null;

        ArrayList<Node> nodes = getNodes(node);

        HashMap<Node, Node> mapping = new HashMap<>();

        for (Node n : nodes){
            mapping.put(n, new Node(n.val));
        }

        for (Node n : nodes){
            Node newNode = mapping.get(n);
            for (Node neighbor : n.neighbors){
                Node newNeighbor = mapping.get(neighbor);
                newNode.neighbors.add(newNeighbor);
            }
        }

        return mapping.get(node);
    }
    private ArrayList<Node> getNodes(Node node){
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();

        queue.offer(node);
        set.add(node);

        while (!queue.isEmpty()){
            Node head = queue.poll();
            for (Node neighbor : head.neighbors){
                if (!set.contains(neighbor)){
                    set.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return new ArrayList<>(set);
    }
}
