import java.util.*;

public class _399EvaluateDivision {
    /*
    DFS
     */
    Map<String, HashMap<String, Double>> g = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations,
                                 double[] values,
                                 List<List<String>> queries) {
        for (int i = 0; i < equations.size(); i++){
            String x = equations.get(i).get(0);
            String y = equations.get(i).get(1);
            double k = values[i];
            g.computeIfAbsent(x, l -> new HashMap<>()).put(y, k);
            g.computeIfAbsent(y, l -> new HashMap<>()).put(x, 1.0 / k);
        }

        double[] ans = new double[queries.size()];

        for (int i = 0; i <queries.size(); i++){
            String x = queries.get(i).get(0);
            String y = queries.get(i).get(1);
            if (!g.containsKey(x) || !g.containsKey(y))
                ans[i] = -1.0;
            else
                ans[i] = divide(x, y, new HashSet<String>());
        }

        return ans;
    }

    private double divide(String x, String y, Set<String> visited){
        if (x.equals(y))
            return 1.0;

        if (!g.containsKey(x))
            return -1.0;

        for (String n : g.get(x).keySet()){
            if (visited.contains(n))
                continue;
            visited.add(n);
            double d = divide(n, y, visited);
            if (d > 0)
                return d * g.get(x).get(n);
        }
        return -1.0;
    }
}

/*
Equations are given in the format A / B = k, where A and B are variables represented as strings.
K is a real number (floating point number).
Given some queries, return the answers.
If the answer does not exist, return -1.0.

 */
