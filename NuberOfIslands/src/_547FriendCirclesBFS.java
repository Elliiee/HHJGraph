import java.util.LinkedList;
import java.util.Queue;

public class _547FriendCirclesBFS {
    /*BFS
    1. We start from a particular node and visited all its directly
    connected nodes first.
    2. After all the direct neighbors have been visited, we apply the same process to
    their neighbor nodes as well.
    3. We make use of a visited array to keep track of the visited nodes.
    4. We increment the count of connected components whenever we need to start off with
    a new node as the root node for applying BFS which hasn't been already visited.
     */

    public int findCircleNum(int[][] M){
        int[] visited = new int[M.length];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < M.length; i++){
            if (visited[i] == 0){
                queue.add(i);
                while (!queue.isEmpty()){
                    int s = queue.remove();
                    visited[s] = 1;
                    for (int j = 0; j < M.length; j++){
                        if (M[s][j] == 1 && visited[j] == 0)
                            queue.add(j);
                    }
                }
                count++;
            }
        }
        return count;
    }
}
