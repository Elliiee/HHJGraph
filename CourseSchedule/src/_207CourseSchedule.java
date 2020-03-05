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
