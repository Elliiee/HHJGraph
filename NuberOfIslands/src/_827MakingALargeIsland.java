import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class _827MakingALargeIsland {
    /*
    Approach 1 naive DFS time limit exceeded
    For each 0, change it to a 1, then do a DFS to find the size of that component.
    The answer is the maximum size component found.
    If there is no 0, of course the answer is the size of the whole grid.
     */
    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, 1, 0, -1};

    public int largestIsland(int[][] grid){
        int N = grid.length;

        int ans = 0;
        boolean hasZero = false;
        for (int r = 0; r < N; r++){
            for (int c = 0; c < N; c++){
                if (grid[r][c] == 0){
                    hasZero = true;
                    grid[r][c] = 1;
                    ans = Math.max(ans, check(grid, r, c));
                    grid[r][c] = 0;
                }
            }
        }
        return hasZero ? ans : N*N;
    }

    public int check(int[][] grid, int r0, int c0){
        int N = grid.length;
        Stack<Integer> stack = new Stack<>();
        Set<Integer> seen = new HashSet<>();
        stack.push(r0 * N + c0);
        seen.add(r0 * N + c0);

        while (!stack.isEmpty()){
            int code = stack.pop();
            int r = code / N, c = code % N;
            for (int k = 0; k < 4; k++){
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (!seen.contains(nr * N + nc) && 0 <= nr && nr < N
                                                && 0 <= nc && nc < N
                                                && grid[nr][nc] == 1){
                    stack.push(nr * N + nc);
                    seen.add(nr * N + nc);
                }
            }
        }
        return seen.size();
    }
}
/*
In a 2D grid of 0s and 1s, we can change at most one 0 to 1.
After the change, what is the size of the largest island?
An island is a 4-directionally connected group of 1s.
 */