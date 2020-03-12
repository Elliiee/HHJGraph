import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _847ShortestPathVisitingAllNodes {
    public int shortestPathLength(int[][] graph){
        int N = graph.length;
        Queue<State> queue = new LinkedList<>();
        int[][] dist = new int[1<<N][N];
        for (int[] row : dist)
            Arrays.fill(row, N*N);

        for (int x = 0; x < N; x++){
            queue.offer(new State(1 << x, x));
            dist[1 << x][x] = 0;
        }

        while (!queue.isEmpty()) {
            State node = queue.poll();
            int d = dist[node.cover][node.head];
            if (node.cover == (1<<N) - 1) return d;

            for (int child: graph[node.head]) {
                int cover2 = node.cover | (1 << child);
                if (d + 1 < dist[cover2][child]) {
                    dist[cover2][child] = d + 1;
                    queue.offer(new State(cover2, child));

                }
            }
        }

        throw null;
    }
}
class State{
    int cover, head;
    State(int c, int h){
        cover = c;
        head = h;
    }
}
/*
Approach 1 BFS
A path 'state' can be represented as the subset of nodes visited, plus the current 'head' node.
Then, the problem reduces to the shortest path problem among these states,
which can be solved with a BFS.
Algorithm
Call the set of nodes visited by a path so far the cover.
Call the current node as the head.
Set bits: k is in the cover if the kth bit of cover is 1.

 */
