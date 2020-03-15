import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TopologicalSort {
    public ArrayList<Node> topSort(ArrayList<Node> graph){
        ArrayList<Node> result = new ArrayList<>();

        // create a map and add nodes to the map
        HashMap<Node, Integer>  map = new HashMap<>();
        for(Node node : graph){
            for (Node neighbor : node.neighbors){
                if (map.containsKey(neighbor))
                    map.put(neighbor, map.get(neighbor) + 1);
                else
                    map.put(neighbor, 1);
            }
        }

        Queue<Node> q = new LinkedList<>(); // create a queue
        for(Node node : graph){  // for each node of the graph
            if (!map.containsKey(node)){ // if the node is not in the map
                q.offer(node);   // add the node to the queue
                result.add(node); // add the node to the result
            }
        }

        while (!q.isEmpty()){
            Node node = q.poll();
            for (Node n : node.neighbors){
                map.put(n, map.get(n) - 1);
                if (map.get(n) == 0){
                    result.add(n);
                    q.offer(n);
                }
            }
        }

        return result;
    }
}
