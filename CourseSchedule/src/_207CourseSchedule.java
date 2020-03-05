import java.awt.font.GraphicAttribute;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class GNode{
    public Integer inDegree = 0;
    public List<Integer> outNodes = new LinkedList<>();
}

public class _207CourseSchedule {
    /*
    Approach 1: Backtracking
    The problem could be modeled as a graph traversal problem.
    Each course can be represented as a vertex.
    The dependency between the courses can be modeled as a directed edge between 2 vertexes.

    If the student can finish all the courses, means there is no cycle existed in the Graph.
    That is called DAG (Directed Acyclic Graph).

    Backtracking is a general algorithm that is often applied to solve the constraint
    satisfaction problems, which incrementally builds candidates to the solutions, and
    abandons a candidate / aka: backtracks as soon as it determines that the candidate
    would not lead to a valid solution.

    Enumerate each vertex to check if it could for a cyclic path. If so, return false as a result.
     */
    public boolean canFinishBacktracking(int numCourses, int[][] prerequisites){
        // course -> list of next courses
        HashMap<Integer, List<Integer>> courseDict = new HashMap<>();

        // build the graph first
        for (int[] relation : prerequisites){
            // relation[0] depends on relation[1]
            if (courseDict.containsKey(relation[1])){
                courseDict.get(relation[1]).add(relation[0]);
            } else {
                List<Integer> nextCourses = new LinkedList<>();
                nextCourses.add(relation[0]);
                courseDict.put(relation[1], nextCourses);
            }
        }

        boolean[] path = new boolean[numCourses];

        for (int currCourse = 0; currCourse < numCourses; currCourse++){
            if (this.isCyclic(currCourse, courseDict, path))
                return false;
        }

        return true;
    }

    /*
    Backtracking to check if there is cyclic
     */
    protected boolean isCyclic(Integer currCourse, HashMap<Integer, List<Integer>> courseDict,
                               boolean[] path){
        if (path[currCourse]) // already in the path, loop
            return true;

        if (!courseDict.containsKey(currCourse)) // no following courses, no loop
            return false;

        path[currCourse] = true; // mark in the path before backtracking

        // backtracking
        boolean ret = false;
        for (Integer nextCourse : courseDict.get(currCourse)){
            ret = this.isCyclic(nextCourse, courseDict, path);
            if (ret)
                break;
        }

        path[currCourse] = false; // remove the node from the path after backtracking.
        return ret;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites){
        /*
        Approach 3: topological sort
        Inorder to find a global order, we can
         */
        if (prerequisites.length == 0)
            return true;

        // course -> list of next courses
        HashMap<Integer, GNode> graph = new HashMap<>();

        // build the graph first
        for (int[] relation : prerequisites){
            GNode prevCourse = this.getCreateGNode(graph, relation[1]);
            GNode nextCourse = this.getCreateGNode(graph, relation[0]);

            prevCourse.outNodes.add(relation[0]);
            nextCourse.inDegree++;
        }

        // we start from courses that have no prerequisites
        int totalDeps = prerequisites.length;
        LinkedList<Integer> nodepCourses = new LinkedList<>();
        for (Map.Entry<Integer, GNode> entry : graph.entrySet()){
            GNode node = entry.getValue();
            if (node.inDegree == 0)
                nodepCourses.add(entry.getKey());
        }

        int removeEdges = 0;
        while (nodepCourses.size() > 0){
            Integer course = nodepCourses.pop();

            for (Integer nextCourse : graph.get(course).outNodes){
                GNode childNode = graph.get(nextCourse);
                childNode.inDegree--;
                if (childNode.inDegree == 0)
                    nodepCourses.add(nextCourse);
            }
        }

        if (removeEdges != totalDeps)
            return false;
        else
            return true;
    }

    /*
    Retrieve the existing <key, value> from graph,
    otherwise create a new one.
     */
    protected GNode getCreateGNode(HashMap<Integer, GNode> graph, Integer course){
        GNode node = null;
        if (graph.containsKey(course)){
            node = graph.get(course);
        } else {
            node = new GNode();
            graph.put(course, node);
        }
        return node;
    }
}

/*
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisite, which is expressed as a pair: [0, 1].
Means if you want to take course 0 you have to take course 1 first.
Find if you can complete all the courses.
 */
