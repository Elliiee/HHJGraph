import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _210CourseScheduleII {
    /*
    DFS
    1. initialize a stack S which will contain the topologically sorted order of the course in our graph.
    2. A pair such as [a, b] represents that the course b needs to be taken in order to do the course a.
    3. For each of the nodes, we will run a DFS search
     */
    static int WHITE = 1;
    static int GRAY = 2;
    static  int BLACK = 3;

    boolean isPossible;
    Map<Integer, Integer> color;
    Map<Integer, List<Integer>> adjList;
    List<Integer> topologicalOrder;

    private void init(int numCourses){
        this.isPossible = true;
        this.color = new HashMap<>();
        this.adjList = new HashMap<>();
        this.topologicalOrder = new ArrayList<>();

        //by default, all vertices are white
        for (int i = 0; i < numCourses; i++){
            this.color.put(i, WHITE);
        }
    }

    private void dfs(int node){
        // stop recursive if we found a cyclic already
        if (!this.isPossible)
            return;

        // start the recursion
        this.color.put(node, GRAY);

        for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<Integer>())){
            if (this.color.get(neighbor) == WHITE)
                this.dfs(neighbor);
            else if (this.color.get(neighbor) == GRAY)
                this.isPossible = false;
        }

        // recursion ends, mark it as BLACK
        this.color.put(node, BLACK);
        this.topologicalOrder.add(node);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites){
        this.init(numCourses);
        for (int i = 0; i < prerequisites.length; i++){
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
            lst.add(dest);
            adjList.put(src, lst);
        }

        // if the node is unprocessed, then call dfs on it
        for (int i = 0; i < numCourses; i++){
            if (this.color.get(i) == WHITE)
                this.dfs(i);
        }

        int[] order;
        if (this.isPossible){
            order = new int[numCourses];
            for (int i = 0; i < numCourses; i++){
                order[i] = this.topologicalOrder.get(numCourses - i - 1);
            }
        } else {
            order = new int[0];
        }

        return order;
    }
}
/*
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you
have to first take course 1, which is expressed as a pair: [0, 1]
Given the total number of courses and a list of prerequisite pairs, return
the ordering of courses you should take to finish all courses.
There are maybe multiple correct orders, you just need to return one of them.
If it is impossible to finish all courses, return an empty array.
 */

/*
Solution
Such a problem is a natural fit for graph based algorithms.
It is a directed, unweighted graph.
The graph is a cyclic graph because there is a possibility of a cycle in the graph.
Topological Sorted Order
 */
